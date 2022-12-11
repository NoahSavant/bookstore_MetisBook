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

import com.metis.book.model.BookRequest;
import com.metis.book.model.Contact;
import com.metis.book.service.IBookRequestService;

@Controller
@RequestMapping("/admin/bookrequest")
public class AdminBookRequestController {
	@Autowired
	IBookRequestService bookRequestService;
	
	@GetMapping
	public ModelAndView viewAdminBookRequestPage(ModelAndView mav) {
		List<BookRequest> bookrequests = bookRequestService.getAllRequest();
		mav.addObject("bookrequests", bookrequests);
		mav.setViewName("admin/bookrequest/bookRequest");
		return mav;
		
	}
	@GetMapping("/insert")
	public ModelAndView viewAddRequestPage(ModelAndView mav) {
		mav.addObject("bookrequest",new BookRequest());
		mav.setViewName("admin/bookrequest/formAddRequest.html");
		return mav;
	}
	@PostMapping("/insert")
	public ModelAndView addRequest(ModelAndView mav, @ModelAttribute("bookrequest") BookRequest bookrequest) {
		bookRequestService.insertBookRequest(bookrequest);
		mav.setViewName("redirect:/admin/bookrequest");
		return mav;
	}
	@DeleteMapping("/delete")
	@ResponseBody
	public String deleteRequest(@RequestParam("bookrequestId") String bookrequestId) {
		bookRequestService.deleteById(Long.parseLong(bookrequestId));
		return "Success";
	}
	@GetMapping("/edit")
	public ModelAndView viewEditRequest(ModelAndView mav, @RequestParam("bookrequestId") String bookrequestId) {
		BookRequest bookrequest =  bookRequestService.getById(Long.parseLong(bookrequestId));
		mav.addObject("bookrequest",bookrequest);
		mav.setViewName("admin/bookrequest/formEditRequest.html");
		return mav;
	}
	@PostMapping("/edit")
	public ModelAndView editRequest(ModelAndView mav, @ModelAttribute("bookrequestId") BookRequest bookrequestId) {
		bookRequestService.editRequest(bookrequestId);
		mav.setViewName("redirect:/admin/bookrequest");
		return mav;
	}
}
