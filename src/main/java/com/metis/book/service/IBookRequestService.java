package com.metis.book.service;

import java.util.List;

import com.metis.book.model.BookRequest;

public interface IBookRequestService {

	List<BookRequest> getAllRequest();

	void insertBookRequest(BookRequest request);

	void deleteById(long parseLong);

	BookRequest getById(long parseLong);

	void editRequest(BookRequest bookrequestId);

}
