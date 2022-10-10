package com.metis.book.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/metis/auth")
@Slf4j
public class AuthController {
	
	@GetMapping("/login")
	public ModelAndView viewLoginPage(
			@RequestParam(name = "error") Optional<String> error) {

		ModelAndView mav = new ModelAndView();
		
		if(error.isPresent()) {
			log.info(error.get());
			mav.addObject("errorMessage", "Username or password not correct");
		}
		
		mav.setViewName("client/login.html");
		return mav;
	}
	
}
