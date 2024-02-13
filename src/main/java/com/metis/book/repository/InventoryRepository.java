package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.Book;
import com.metis.book.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Inventory findByBook(Book book);

}
