package com.metis.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.metis.book.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value =  "/checkout")
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
	
	@GetMapping
	public ModelAndView viewOrderPage(ModelAndView mav) {
		
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		renderObject(mav,userPrincipal.getId());
		mav.setViewName("/client/checkout.html");
		return mav;
	}
	
	@PostMapping(path = "/update")
	public ModelAndView viewUpdateSection(
			ModelAndView mav,
			@ModelAttribute("checkoutForm") CheckoutForm checkoutForm,
			BindingResult result) {
		log.info(checkoutForm.toString());
		userService.updateCheckout(checkoutForm);
		mav.setViewName("redirect:/checkout");
		return mav;
	}
	private void renderObject(ModelAndView mav, Long userId) {
		Cart cart = cartService.getCartByUser(userId);
		User user = userService.getUserById(userId);
		List<Address> addresses = addressService.getAddressByUser(user);
		CheckoutForm checkoutForm = convert(user);
		List<CartItem> cartItems = cart.getCartItems();
		
		
		if(lackOfInfo(checkoutForm)) {
			mav.addObject("lackInfo",true);
		}
		
		mav.addObject("addresses",addresses);
		mav.addObject("checkoutForm",checkoutForm);
		mav.addObject("cart",cart);
		mav.addObject("cartItems",cartItems);
	}
	
	private CheckoutForm convert(User user) {
		CheckoutForm checkoutForm = new CheckoutForm();
		checkoutForm.setFirstName(user.getFirstName());
		checkoutForm.setLastName(user.getLastName());
		checkoutForm.setEmail(user.getEmail());
		checkoutForm.setUsername(user.getUsername());
		List<Address> addresses = addressService.getAddressByUser(user);
		log.info("before address");
		for (Address address : addresses) {
			log.info("In address");
			if(address.getIsPrimary()) {
				log.info("In primary address");
				checkoutForm.setFullAddress(address.getFullAddress());
				checkoutForm.setDistrict(address.getDistrict());
				checkoutForm.setSubDistrict(address.getSubDistrict());
				checkoutForm.setStreet(address.getStreet());
				checkoutForm.setProvince(address.getProvince());
				break;
			}
		}
		checkoutForm.setPhoneNumber(user.getPhoneNumber());
		
		return checkoutForm;
	}
	
	private Boolean lackOfInfo(CheckoutForm checkoutForm) {
		
		if(checkoutForm.getFullAddress()==null || checkoutForm.getPhoneNumber()==null) {
			return true;
		}
		return false;
	}
}
