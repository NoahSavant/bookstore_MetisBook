package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.CartItem;

@Repository
public interface CartItemReposirory extends JpaRepository<CartItem, Long> {

}
