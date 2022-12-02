package com.metis.book.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.model.user.Address;
import com.metis.book.model.user.User;
import com.metis.book.security.UserPrincipal;
import com.metis.book.service.IAddressService;
import com.metis.book.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/member")
@Controller
@Slf4j
public class AddressController {

	@Autowired
	IAddressService addressService;
	
	@Autowired
	IUserService userService;
	
	@GetMapping("/address")
	public ModelAndView viewAddressPage(ModelAndView mav) {
		
		
		// Get authenticated user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		User user = userService.getUserById(userPrincipal.getId());
		
		List<Address> addresses = addressService.getAddressByUser(user);
		if(!addresses.isEmpty()) {
			for (Address address : addresses) {
				log.error("Vô đây ne");
				address.setFullAddress(address.getFullAddress()==null?null:address.fetchFullAddress());
			}
		}
		log.error("Ra đây ne");
		mav.addObject("addresses", addresses);
		mav.setViewName("client/address.html");
		return mav;
	}
	
	@GetMapping("/address-detail")
	public ModelAndView viewUpdateAddressPage(
			ModelAndView mav,
			@RequestParam("id") String  addressId) {
		
		Address address = addressService.getAddressById(Long.parseLong(addressId));
		mav.addObject("address",address);
		mav.setViewName("client/address-detail.html");
		return mav;
	}
	
	@PostMapping("/address-detail")
	public ModelAndView updateAddress(
			ModelAndView mav,
			@ModelAttribute("address") Address address) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		User user = userService.getUserById(userPrincipal.getId());
		addressService.updateAddress(address,user);
		mav.setViewName("redirect:/member/address");
		return mav;
	}
}
