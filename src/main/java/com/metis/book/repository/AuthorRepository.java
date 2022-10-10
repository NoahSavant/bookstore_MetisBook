package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

	Author findByName(String string);

}
