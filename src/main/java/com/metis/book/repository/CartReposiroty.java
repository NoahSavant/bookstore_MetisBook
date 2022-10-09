package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.Cart;

@Repository
public interface CartReposiroty extends JpaRepository<Cart, Long> {

}
