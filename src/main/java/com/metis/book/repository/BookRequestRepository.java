package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.BookRequest;
@Repository
public interface BookRequestRepository extends JpaRepository<BookRequest, Long> {

}
