package com.metis.book.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/user")
@Slf4j
public class AdminUserController {
	@GetMapping("/")
	public ModelAndView bookView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/user.html");
		return mav;
	}

}
