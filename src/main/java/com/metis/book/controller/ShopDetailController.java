package com.metis.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.dto.BookForm;
import com.metis.book.model.Book;
import com.metis.book.service.IBookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/shop-detail")
public class ShopDetailController {

	@Autowired
	IBookService bookService;
	
	@GetMapping
	public ModelAndView viewShopDetailPage(
			ModelAndView mav,
			@RequestParam("bookId") String bookId) {
		
		
		BookForm book = bookService.getById(Long.parseLong(bookId)); 
		int sold = bookService.getSoldNumberById(Long.parseLong(bookId));
		List<Book> topFeatured = bookService.getTopFeatured();
		mav.addObject("topFeatured",topFeatured);
		mav.addObject("sold",sold);
		mav.addObject("book",book);
		mav.setViewName("client/shop-detail");
		return mav;
	}
}
