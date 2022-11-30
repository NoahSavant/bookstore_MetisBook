package com.metis.book.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.dto.BookForm;
import com.metis.book.model.Book;
import com.metis.book.model.Feedback;
import com.metis.book.service.IBookService;
import com.metis.book.service.IFeedbackService;

import net.bytebuddy.agent.builder.AgentBuilder.FallbackStrategy.Simple;

@Controller
@RequestMapping("/shop-detail")
public class ShopDetailController {

	@Autowired
	IBookService bookService;
	
	@Autowired
	IFeedbackService feedbackService;
	
	@GetMapping
	public ModelAndView viewShopDetailPage(
			ModelAndView mav,
			@RequestParam("bookId") String bookId) throws ParseException {
		
		
		BookForm book = bookService.getById(Long.parseLong(bookId)); 
		int sold = bookService.getSoldNumberById(Long.parseLong(bookId));
		List<Book> topFeatured = bookService.getTopFeatured();
		List<Feedback> feedbacks = feedbackService.getFeedbacksById(Long.parseLong(bookId));
		
		mav.addObject("feedbacks", feedbacks);
		mav.addObject("topFeatured",topFeatured);
		mav.addObject("sold",sold);
		mav.addObject("book",book);
		Feedback feedback = new Feedback();
		feedback.setRating(5);
		mav.addObject("feedback",feedback);
		mav.setViewName("client/shop-detail");
		return mav;
	}
}
