package com.metis.book.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.dto.CheckoutForm;
import com.metis.book.model.Cart;
import com.metis.book.model.CartItem;
import com.metis.book.model.user.Address;
import com.metis.book.model.user.User;
import com.metis.book.security.UserPrincipal;
import com.metis.book.service.IAddressService;
import com.metis.book.service.ICartItemService;
import com.metis.book.service.ICartService;
import com.metis.book.service.IOrderService;
import com.metis.book.service.IUserService;
import com.metis.book.utils.AppConstant;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value =  "member/checkout")
@Slf4j
public class CheckoutController {

	@Autowired
	ICartService cartService;
	
	@Autowired
	ICartItemService cartItemService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IAddressService addressService;
	
	
	@Autowired
	IOrderService orderService;
	
	@PostMapping
	public ModelAndView viewCheckoutPage(
			ModelAndView mav,
			@ModelAttribute("checkoutForm") CheckoutForm checkoutForm,
			HttpSession session) {
		
		if(Objects.isNull(checkoutForm) || checkoutForm.getCheckoutItems().size()<=0) {
			session.setAttribute("error","true");
			mav.setViewName("redirect:/member/cart");
			return mav;
		}
		if(getStatus(checkoutForm) != 0) {
			session.setAttribute("error-bookId",getStatus(checkoutForm));
			mav.setViewName("redirect:/member/cart");
			return mav;
		}
		
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		renderObject(mav,userPrincipal.getId(),checkoutForm);
		
		mav.setViewName("client/checkout.html");
		return mav;
	}
	
	private int getStatus(CheckoutForm checkoutForm) {
		
		List<String> cartItems = checkoutForm.getCheckoutItems();
		return cartService.getStatus(cartItems);
	}
	
	
	
	@PostMapping(path = "/update")
	public ModelAndView updateCheckoutInfo(
			ModelAndView mav,
			@ModelAttribute("checkoutForm") CheckoutForm checkoutForm,
			BindingResult result) {
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		mav.setViewName("client/checkout.html");
		if(checkoutForm.getRecievePhoneNumber()==null||checkoutForm.getNewAddress()==null) {
			mav.addObject("lackInfo",true);
			return mav;
		}
		userService.updateCheckout(checkoutForm);
		renderObject(mav,userPrincipal.getId(),checkoutForm);
		
		return mav;
	}
	
	@PostMapping("/pay")
	public ModelAndView paymentProcessing(
			ModelAndView mav,
			@ModelAttribute("checkoutForm") CheckoutForm checkoutForm,
			@RequestParam("delivery_cost") String delivery_cost) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		checkoutForm.setDeliverCost(delivery_cost);
		if(lackOfInfo(checkoutForm)) {
			mav.addObject("lackInfo",true);
			renderObject(mav,userPrincipal.getId(),checkoutForm);
			mav.setViewName("client/checkout.html");
			return mav;
		}
		Long orderId = orderService.createOrder(checkoutForm);
		
		mav.setViewName("redirect:/member/order-detail?orderId="+orderId);
		return mav;
	}
	
	private void renderObject(ModelAndView mav, Long userId,CheckoutForm checkoutForm) {
		Cart cart = cartService.getCartByUser(userId);
		User user = userService.getUserById(userId);
		List<Address> addresses = addressService.getAddressByUser(user);
		for (Address address : addresses) {
			address.setFullAddress(address.fetchFullAddress());
		}
		checkoutForm = convert(user,checkoutForm);
		
		if(lackOfInfo(checkoutForm)) {
			mav.addObject("lackInfo",true);
		}
		
		List<String> itemId = checkoutForm.getCheckoutItems();
		List<CartItem> cartItems = new ArrayList<>();
		for (String item : itemId) {
			CartItem cartItem =  cartItemService.getItemById(Long.parseLong(item));
			if(Objects.nonNull(cartItems)) {
				cartItems.add(cartItem);
			}
		}
		renderDeliverFee(mav);
		mav.addObject("addresses",addresses);
		mav.addObject("checkoutForm",checkoutForm);
		mav.addObject("cart",cart);
		mav.addObject("cartItems",cartItems);
		
	}
	
	private void renderDeliverFee(ModelAndView mav) {
		mav.addObject("Standard",AppConstant.STANDARD);
		mav.addObject("Fast",AppConstant.FAST);
		mav.addObject("VeryFast",AppConstant.VERY_FAST);
	}
	
	private CheckoutForm convert(User user, CheckoutForm checkoutForm) {

		checkoutForm.setFirstName(user.getFirstName());
		checkoutForm.setLastName(user.getLastName());
		checkoutForm.setEmail(user.getEmail());
		checkoutForm.setUsername(user.getUsername());
		if(checkoutForm.getDeliverMethod()==null) {
			checkoutForm.setPaymentMethod("Cash");
		}
		if(checkoutForm.getDeliverMethod()==null) {
			checkoutForm.setDeliverMethod("Tiêu chuẩn");
		}
		
		
		List<Address> addresses = addressService.getAddressByUser(user);
		for (Address address : addresses) {
			if(address.getIsPrimary()) {
				checkoutForm.setFullAddress(address.fetchFullAddress());
				checkoutForm.setDistrict(address.getDistrict());
				checkoutForm.setSubDistrict(address.getSubDistrict());
				checkoutForm.setStreet(address.getStreet());
				checkoutForm.setProvince(address.getProvince());
				checkoutForm.setRecievePhoneNumber(address.getRecievePhoneNumber());
				break;
			}
		}
		checkoutForm.setPhoneNumber(user.getPhoneNumber());
		
		return checkoutForm;
	}
	
	private Boolean lackOfInfo(CheckoutForm checkoutForm) {
		
		if(checkoutForm.getFullAddress()==null || checkoutForm.getFullAddress().equals("-1")) {

			return true;
		}
		return false;
	}
}
