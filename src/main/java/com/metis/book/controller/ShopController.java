package com.metis.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.model.Book;
import com.metis.book.model.Category;
import com.metis.book.service.IBookService;
import com.metis.book.service.ICategoryService;

@Controller
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private IBookService bookService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping
	public ModelAndView viewCartPage() {
		List<Book> books = bookService.getAllBooks();
		List<String> publishers = bookService.getAllPublishers();
		List<Category> categories = categoryService.getAllCategories();
		Long maxPrice = bookService.getMaxPrice();
		Long numAllBooks = bookService.getNumAllBooks();
		ModelAndView mav = new ModelAndView();
		mav.addObject("books", books);
		mav.addObject("publishers", publishers);
		mav.addObject("categories", categories);
		mav.addObject("maxPrice", maxPrice);
		mav.addObject("numAllBooks", numAllBooks);
		mav.setViewName("/client/shop");
		return mav;
	}
}
