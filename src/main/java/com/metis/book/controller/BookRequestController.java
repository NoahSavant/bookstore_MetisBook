package com.metis.book.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.model.BookRequest;
import com.metis.book.service.IBookRequestService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/bookrequest")
@Slf4j
public class BookRequestController {
	@Autowired
	IBookRequestService bookRequestService;
	@GetMapping
	public ModelAndView viewBookRequest(ModelAndView mav)
	{
		BookRequest request = new BookRequest();
		mav.addObject("request",request);
		mav.setViewName("client/book-request");
		return mav;
	}
	@PostMapping
	public ModelAndView addBookRequest(ModelAndView mav, @ModelAttribute("request") BookRequest request)
	{
		log.info(request.toString());
		mav.setViewName("client/book-request");
		bookRequestService.insertBookRequest(request);
		mav.addObject("isSucceed", true);
		mav.addObject("request", new BookRequest());
		return mav;
	}
	

}
