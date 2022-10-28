package com.metis.book.service;

import java.util.List;

import com.metis.book.model.order.Order;

public interface IOrderService {

	List<Order> getAllOrderByUser();

}
