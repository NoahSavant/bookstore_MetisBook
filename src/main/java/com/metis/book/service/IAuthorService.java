package com.metis.book.service;

import java.util.List;

import javax.validation.Valid;

import com.metis.book.dto.AuthorForm;
import com.metis.book.model.Author;

public interface IAuthorService {

	List<Author> getAllAuthors();

	void insert(@Valid AuthorForm author);

    List<AuthorForm> getAuthorShows();

    AuthorForm getById(long parseLong);

    void updateAuthor(AuthorForm authorForm);
}
