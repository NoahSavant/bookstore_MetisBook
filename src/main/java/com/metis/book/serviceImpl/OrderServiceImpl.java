package com.metis.book.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.model.order.Order;
import com.metis.book.repository.OrderRepository;
import com.metis.book.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService{

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<Order> getAllOrderByUser() {
		return orderRepository.findAll();
	}

}
