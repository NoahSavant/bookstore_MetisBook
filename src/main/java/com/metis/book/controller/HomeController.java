package com.metis.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/metis/home")
public class HomeController {

	
	@GetMapping
	public String home() {
		return "admin/order.html";
	}
}
