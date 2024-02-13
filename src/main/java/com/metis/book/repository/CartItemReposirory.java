package com.metis.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.Book;
import com.metis.book.model.CartItem;

@Repository
public interface CartItemReposirory extends JpaRepository<CartItem, Long> {

    List<CartItem> findByBook(Book book);

}