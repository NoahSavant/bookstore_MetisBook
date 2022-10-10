package com.metis.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/cart")
public class CartController {

	@GetMapping
	public ModelAndView viewCartPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/client/cart");
		return mav;
	}
}
