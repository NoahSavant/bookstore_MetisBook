package com.metis.book.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.metis.book.dto.OrderShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.metis.book.dto.CheckoutForm;
import com.metis.book.model.CartItem;
import com.metis.book.model.order.Order;
import com.metis.book.model.order.OrderItem;
import com.metis.book.model.order.OrderTrack;
import com.metis.book.model.user.User;
import com.metis.book.repository.CartItemReposirory;
import com.metis.book.repository.OrderItemRepository;
import com.metis.book.repository.OrderRepository;
import com.metis.book.repository.OrderTrackRepository;
import com.metis.book.repository.UserRepository;
import com.metis.book.security.UserPrincipal;
import com.metis.book.service.IOrderService;
import com.metis.book.utils.AppConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService{

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	CartItemReposirory cartItemRepository;
	
	@Autowired
	OrderTrackRepository trackRepository;

	@Override
	public List<Order> getAllOrderByUser() {

		return orderRepository.findAll();
	}

	@Override
	public Long createOrder(CheckoutForm checkoutForm) {
		
		User user = userRepository.findByEmail(checkoutForm.getEmail());
		if(Objects.isNull(user)) {
			log.error(AppConstant.USER_NOT_FOUND + checkoutForm.getEmail());
		}
		
		List<String> itemsId = checkoutForm.getCheckoutItems();
		List<CartItem> cartItems = new ArrayList<>();
		
		
		for (String item : itemsId) {
			CartItem cartItem =  cartItemRepository.findById(Long.parseLong(item)).get();
			if(Objects.nonNull(cartItem)) {
				cartItems.add(cartItem);
				// delete that item in cart to transfer to order
				cartItemRepository.deleteById(Long.parseLong(item));
			}
		}
		
		
		OrderTrack orderTrack = trackRepository.findByStatus("Đang chuẩn bị");
		
		
		Order order = new Order();
		String deliverMethod = checkoutForm.getDeliverMethod();
		order.setUser(user);
		order.setDeliverMethod(deliverMethod);
		order.setPaymentMethod(checkoutForm.getPaymentMethod());
		order.setOrderTrack(orderTrack);
		order.setOrderDate(new Date());
		
		orderRepository.save(order);
		List<OrderItem> orderItems = convertToOrderItem(order,cartItems);
		List<OrderItem> orderItemsSaved = orderItemRepository.saveAll(orderItems);
		
		order.setOrderItems(orderItemsSaved);
		order.setTotalPrice(order.getTotalPrice(deliverMethod));
		log.info(order.getTotalPrice().toString());
		orderRepository.save(order);
		// update cart item
		Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		userPrincipal.setCartItemNum(String.valueOf(checkoutForm.getCheckoutItems().size()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return order.getId();
	}

	
	private List<OrderItem> convertToOrderItem(Order order,List<CartItem> cartItems) {
		
		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItem cartItem : cartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setBook(cartItem.getBook());
			orderItem.setOrder(order);
			orderItems.add(orderItem);
		}
		return orderItems;
		
	}

	@Override
	public Order getOrderById(Long orderId) {
		
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
										.getPrincipal();
		
		User user = userRepository.findById(userPrincipal.getId()).get();
		if(Objects.isNull(user)) {
			log.error(AppConstant.USER_NOT_FOUND+ userPrincipal.getId());
			return null;
		}
		
		
		// check if authenticated user has that order
		Optional<Order> order = orderRepository.findById(orderId);
		
		if(order.isEmpty()) {
			log.error("Not found order");
			return null;
		}else {
			User userChecked  = userRepository.findByOrders(order.get());
			if(!user.getId().equals(userChecked.getId())) {
				log.error("Not found order");
				return null;
			}
		}
		return order.get();
	}

	@Override
	public List<OrderShow> getOrderShows() {
		List<User> users = userRepository.findAll();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		List<OrderShow> orderShows = new ArrayList<>();
		for (User user: users) {
			for (Order order: user.getOrders()) {
				OrderShow orderShow = new OrderShow();
				orderShow.setUsername(user.getUsername());
				orderShow.setOrder(order);
				orderShow.setOrderDate(formatter.format(order.getOrderDate()));
				orderShows.add(orderShow);
			}
		}
		return orderShows;
	}
}
