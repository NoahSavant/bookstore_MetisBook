package com.metis.book.service;

import java.util.List;

import com.metis.book.dto.CheckoutForm;
import com.metis.book.dto.OrderShow;
import com.metis.book.model.order.Order;

public interface IOrderService {

	List<Order> getAllOrderByUser();

	Long createOrder(CheckoutForm checkoutForm);

	Order getOrderById(Long orderId);

	List<OrderShow> getOrderShows();
}
