package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	Book findByTitle(String string);
	
	
}
