package com.metis.book.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

@Controller
@RequestMapping("/member/profile")
@Slf4j
public class ProfileController {

	@Autowired
	IAddressService addressService;

	@Autowired
	IUserService userService;

	@GetMapping
	public ModelAndView viewProfilePage(ModelAndView mav) {

		// get required objects
		mav = renderObjects();
		mav.addObject("isShow", true);
		return mav;
	}

	@PostMapping
	public ModelAndView editProfile(
			ModelAndView mav,
			@RequestParam("type") String type) {

		log.info(type);
		if (type.equals("edit")) {
			// get required objects
			mav = renderObjects();
			mav.addObject("isEdit",true);
			return mav;
		}
		
		
		mav.addObject("isShow",true);
		mav.setViewName("client/profile");
		return mav;
	}

	private Address findPrimaryAddress(UserPrincipal userPrincipal) {
		User user = userService.getUserById(userPrincipal.getId());

		List<Address> addresses = addressService.getAddressByUser(user);
		if (Objects.isNull(addresses)) {
			return null;
		}

		for (Address address : addresses) {
			if (address.getIsPrimary()) {
				return address;
			}
		}
		return null;

	}

	private ModelAndView renderObjects() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		ModelAndView mav = new ModelAndView();
		Address address = findPrimaryAddress(userPrincipal);
		if (Objects.isNull(address)) {
			log.error("Address is null");
		}
		mav.addObject("address", address);
		mav.addObject("user", userPrincipal);
		mav.setViewName("client/profile");
		return mav;
	}
}
