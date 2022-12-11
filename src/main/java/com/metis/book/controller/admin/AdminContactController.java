package com.metis.book.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.model.Contact;
import com.metis.book.service.IContactService;

@Controller
@RequestMapping("/admin/contact")
public class AdminContactController {
	@Autowired
	IContactService contactService;
	
	@GetMapping
	public ModelAndView viewAdminContactPage(ModelAndView mav) {
		List<Contact> contacts = contactService.getAllContacts();
		mav.addObject("contacts", contacts);
		mav.setViewName("admin/contact/contact.html");
		return mav;
	}
	@GetMapping("/insert")
	public ModelAndView viewAddContact(ModelAndView mav) {
		mav.addObject("contact",new Contact());
		mav.setViewName("admin/contact/formAddContact.html");
		return mav;
	}
	@PostMapping("/insert")
	public ModelAndView addContact(ModelAndView mav, @ModelAttribute("contact") Contact contact) {
		contactService.insertContact(contact);
		mav.setViewName("redirect:/admin/contact");
		return mav;
	}
	@DeleteMapping("/delete")
	@ResponseBody
	public String deleteContact(@RequestParam("contactId") String contactId) {
		contactService.deleteById(Long.parseLong(contactId));
		return "Success";
	}
	@GetMapping("/edit")
	public ModelAndView viewEditContact(ModelAndView mav, @RequestParam("contactId") String contactId) {
		Contact contact =  contactService.getById(Long.parseLong(contactId));
		mav.addObject("contact",contact);
		mav.setViewName("admin/contact/formEditContact.html");
		return mav;
	}
	@PostMapping("/edit")
	public ModelAndView editContact(ModelAndView mav, @ModelAttribute("contact") Contact contact) {
		contactService.editContact(contact);
		mav.setViewName("redirect:/admin/contact");
		return mav;
	}
}
