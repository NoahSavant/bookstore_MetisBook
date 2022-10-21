package com.metis.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.model.Category;
import com.metis.book.service.ICategoryService;


@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	ICategoryService categoryService;
	
	@GetMapping
	public ModelAndView home(ModelAndView mav) {
		List<Category> categories = categoryService.getAllCategories();
		mav.addObject("categories", categories);
		mav.setViewName("client/index.html");
		return mav;
	}
}
