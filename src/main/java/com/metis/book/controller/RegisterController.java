package com.metis.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("metis/register")
public class RegisterController {

	@GetMapping
	public String Register() {
		return "client/register.html";
	}
}
