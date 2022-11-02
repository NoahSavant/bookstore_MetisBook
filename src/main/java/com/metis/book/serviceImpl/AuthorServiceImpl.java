package com.metis.book.serviceImpl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.dto.AuthorForm;
import com.metis.book.model.Author;
import com.metis.book.repository.AuthorRepository;
import com.metis.book.service.IAuthorService;

@Service

public class AuthorServiceImpl implements IAuthorService {
	@Autowired
	AuthorRepository authorRepository;
	
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
	
}
