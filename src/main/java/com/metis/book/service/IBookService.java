package com.metis.book.service;



import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.metis.book.dto.BookForm;
import com.metis.book.model.Book;

public interface IBookService {

	void insert(BookForm bookForm) throws ParseException, IOException;

	List<Book> getTopFeatured();

	List<Book> getBestSeller();

	List<Book> getAllBooks();

	List<String> getAllPublishers();

	Long getMaxPrice();

	Long getNumAllBooks();

	List<BookForm> getBookShows();
	

}
