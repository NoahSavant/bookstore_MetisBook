package com.metis.book.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.text.StyledEditorKit;
import javax.validation.Valid;

import com.metis.book.dto.CategoryForm;
import com.metis.book.model.Category;
import com.metis.book.repository.UserRepository;
import com.metis.book.utils.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.dto.AuthorForm;
import com.metis.book.model.Author;
import com.metis.book.repository.AuthorRepository;
import com.metis.book.service.IAuthorService;

@Service
@Slf4j
public class AuthorServiceImpl implements IAuthorService {
	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public void insert(@Valid AuthorForm authorForm) {
		Author author = new Author();
		author.setName(authorForm.getName());
		authorRepository.save(author);
		
	}

	@Override
	public List<AuthorForm> getAuthorShows() {
		List<Author> authors = authorRepository.findAll();
		List<AuthorForm> authorForms = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		for (Author author: authors) {
			AuthorForm authorForm = new AuthorForm();
			authorForm.setId(author.getId().toString());
			authorForm.setName(author.getName());
			if (author.getCreateBy()!=null){
				authorForm.setCreateBy(userRepository.findById(author.getCreateBy()).get().getUsername());
			} else {
				authorForm.setCreateBy("");
			}
			if (author.getUpdateBy()!=null){
				authorForm.setLastUpdateBy(userRepository.findById(author.getUpdateBy()).get().getUsername());
			} else {
				authorForm.setLastUpdateBy("");
			}
			authorForm.setCreateDate(formatter.format(author.getCreatedAt()));
			authorForm.setLastUpdateDate(formatter.format(author.getUpdatedAt()));
			authorForms.add(authorForm);
		}
		return authorForms;
	}

	@Override
	public AuthorForm getById(long id) {
		Author author = authorRepository.findById(id).get();
		AuthorForm authorForm = new AuthorForm();
		if (Objects.isNull(author)) {
			log.error(AppConstant.BOOK_NOT_FOUND + " " + id);
		}
		authorForm.setId(author.getId().toString());
		authorForm.setName(author.getName());
		return authorForm;
	}

	@Override
	public void updateAuthor(AuthorForm authorForm) {
		Author author = authorRepository.findById(Long.parseLong(authorForm.getId())).get();
		if (Objects.isNull(author)) {
			log.error(AppConstant.BOOK_NOT_FOUND + " " + authorForm.getId());
		}
		author.setName(authorForm.getName());
		authorRepository.save(author);
	}


}
