package com.metis.book.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
