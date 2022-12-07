package com.metis.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.Book;
import com.metis.book.model.order.Order;
import com.metis.book.model.order.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

	List<OrderItem> findByBook(Book book);

	Boolean existsByBookAndOrder(Book book, Order order);

}
