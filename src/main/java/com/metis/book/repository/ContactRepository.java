package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
