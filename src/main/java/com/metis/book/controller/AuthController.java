package com.metis.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/metis/auth")
public class AuthController {
	
	@GetMapping("/login")
	public String Login() {
		return "client/login.html";
	}
	
	@GetMapping("/register")
	public String Register() {
		return "client/register.html";
	}
}
