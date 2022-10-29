package com.metis.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.model.Book;
import com.metis.book.service.IBookService;

@Controller
@RequestMapping("/member/cart")
public class CartController {

	@Autowired
	private IBookService bookService;
	
	@GetMapping
	public ModelAndView viewCartPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/client/cart");
		return mav;
	}
}
