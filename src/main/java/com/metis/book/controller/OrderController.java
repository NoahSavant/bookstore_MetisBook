package com.metis.book.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.model.order.Order;
import com.metis.book.service.IOrderService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/order-detail")
@Slf4j
public class OrderController {

	@Autowired
	IOrderService orderService;
	
	@GetMapping
	public ModelAndView viewOrderDetailPage(
			ModelAndView mav,
			@RequestParam("orderId") String orderId) {
		
		Order order = orderService.getOrderById(Long.parseLong(orderId));
		if(Objects.isNull(order)) {
			// for temp, redirect to cart page if order id not valid
			mav.setViewName("redirect:/member/cart");
			return mav;
		}
		mav.addObject("order",order);
		mav.setViewName("/client/order-detail.html");
		return mav;
	}
}
