package com.metis.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/metis/login")
public class LoginController {

	@GetMapping
	public String Login() {
		return "client/login.html";
	}
}
