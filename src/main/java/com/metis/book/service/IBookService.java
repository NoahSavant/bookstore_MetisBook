package com.metis.book.service;



import java.io.IOException;
import java.text.ParseException;

import com.metis.book.dto.BookForm;

public interface IBookService {

	void insert(BookForm bookForm) throws ParseException, IOException;

}
