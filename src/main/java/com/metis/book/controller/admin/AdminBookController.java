package com.metis.book.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/book")
public class AdminBookController {

	@GetMapping("/")
	public ModelAndView bookView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/book.html");
		return mav;
	}
	
	@GetMapping("/insert")
	public ModelAndView insert() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/formAddProduct.html");
		return mav;
	}
}
