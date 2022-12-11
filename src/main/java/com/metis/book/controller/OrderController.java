package com.metis.book.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.model.order.Order;
import com.metis.book.model.user.User;
import com.metis.book.security.UserPrincipal;
import com.metis.book.service.IOrderService;
import com.metis.book.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/order-detail")
@Slf4j
public class OrderController {

	@Autowired
	IOrderService orderService;
	
	@Autowired
	IUserService userService;
	
	@GetMapping
	public ModelAndView viewOrderDetailPage(
			ModelAndView mav,
			@RequestParam("orderId") String orderId) {
		log.error("VÔ đây");
		Order order = orderService.getOrderById(Long.parseLong(orderId));
		User user = userService.findByOrder(order);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		if(!user.getId().equals(userPrincipal.getId())) {
			throw new AccessDeniedException("Bạn không có quyền truy cập");
		}
		log.error("VÔ đây");
		if(Objects.isNull(order)) {
			// for temp, redirect to cart page if order id not valid
			mav.setViewName("redirect:/member/cart");
			return mav;
		}
		mav.addObject("order",order);
		mav.setViewName("client/order-detail.html");
		return mav;
	}
}
