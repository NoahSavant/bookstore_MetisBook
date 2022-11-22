package com.metis.book.service;



import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.metis.book.dto.BookForm;
import com.metis.book.dto.FilterForm;
import com.metis.book.model.Book;

public interface IBookService {

	void insert(BookForm bookForm) throws ParseException, IOException;

	List<Book> getTopFeatured();

	List<Book> getBestSeller();

	List<Book> getAllBooks();

	List<String> getAllPublishers();

	Long getMaxPrice();

	Long getNumAllBooks();

	List<Book> getBooksByCategory(String category);

	List<BookForm> getBookShows();

	void deleteById(Long bookId);

	BookForm getById(Long parseLong);

	void updateBook(BookForm bookForm) throws ParseException, IOException;

	List<Book> filter(List<Book> books, FilterForm filterForm);

	int getSoldNumberById(Long bookId);
}
