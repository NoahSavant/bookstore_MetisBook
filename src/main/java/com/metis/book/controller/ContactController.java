package com.metis.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.model.Contact;
import com.metis.book.service.IContactService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/contact")
@Slf4j
public class ContactController {

	@Autowired
	IContactService contactService;
	@GetMapping
	public ModelAndView viewContactPage(ModelAndView mav) {
		Contact contact = new Contact();
		mav.addObject("contact", contact);
		mav.setViewName("client/contact-us");
		return mav;
	}
	
	@PostMapping
	public ModelAndView addContact(ModelAndView mav, @ModelAttribute("contact") Contact contact) {
		log.info(contact.toString());
		contactService.insertContact(contact);
		mav.setViewName("client/contact-us");
		mav.addObject("isSucceed", true);
		mav.addObject("contact", new Contact());
		return mav;
	}
}
