package com.metis.book.controller.admin;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.dto.BookForm;
import com.metis.book.model.Author;
import com.metis.book.model.Category;
import com.metis.book.model.Language;
import com.metis.book.service.IAuthorService;
import com.metis.book.service.IBookService;
import com.metis.book.service.ICategoryService;
import com.metis.book.service.ILanguageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/book")
@Slf4j
public class AdminBookController {
	
	@Autowired
	IAuthorService authorService;
	@Autowired
	ILanguageService languageService;
	@Autowired
	IBookService bookService;
	@Autowired
	ICategoryService categoryService;

	@GetMapping("/")
	public ModelAndView bookView() {
		ModelAndView mav = new ModelAndView();
		List<BookForm> bookForms = bookService.getBookShows();
		log.info(bookForms.get(0).toString());
		mav.addObject("books", bookForms);
		mav.setViewName("/admin/book/book.html");
		return mav;
	}
	
	@GetMapping("/insert")
	public ModelAndView viewInsertBook() {
		ModelAndView mav = new ModelAndView();
		BookForm bookForm = new BookForm();
		List<Author> authors =  authorService.getAllAuthors();
		mav.addObject("authors", authors);
		List<Language> languages = languageService.getAllLanguages();
		mav.addObject("languages", languages);
		List<Category> categories = categoryService.getAllCategories();
		mav.addObject("categories", categories);
		mav.addObject("bookForm", bookForm);
		mav.setViewName("/admin/book/formAddBook.html");
		return mav;
	}
	@PostMapping("/add")
	public ModelAndView insert(@Valid @ModelAttribute("bookForm") BookForm bookForm, BindingResult result) throws ParseException, IOException {
		log.info(bookForm.toString());
		ModelAndView mav = new ModelAndView();
		
		if (result.hasErrors()) {
			log.info("lỗi");
			mav.setViewName("redirect:/admin/book/insert");
			return mav;
		}
		bookService.insert(bookForm);
		mav.setViewName("redirect:/admin/book/");
		
		return mav;
	}
	
	@DeleteMapping("/delete")
	public ModelAndView deleteBook(
			ModelAndView mav,
			@RequestParam("bookId") String bookId) {	
		bookService.deleteById(Long.parseLong(bookId));
		mav.setViewName("/admin/book/book.html");
		return mav;
	}
	
	@GetMapping("/edit")
	public ModelAndView viewUpdateBook(
			ModelAndView mav,
			@RequestParam("bookId") String bookId) {	
		
		BookForm bookForm = bookService.getById(Long.parseLong(bookId));
		List<Category> categories = categoryService.getAllCategories();
		mav.addObject("categories", categories);
		List<Language> languages = languageService.getAllLanguages();
		mav.addObject("languages", languages);
		List<Author> authors = authorService.getAllAuthors();
		mav.addObject("authors", authors);
		mav.addObject("bookForm", bookForm);
		log.info(bookForm.getAuthors().get(0));
		mav.setViewName("/admin/book/formEditBook.html");
		return mav;
	}
	
	@PostMapping("/edit")
	public ModelAndView updateBook(
			ModelAndView mav, 
			@ModelAttribute("bookForm") BookForm bookForm, BindingResult result) throws ParseException, IOException {	
		bookService.updateBook(bookForm);
		mav.addObject("updateSucceed", true);
		mav.setViewName("redirect:/admin/book/");
		return mav;
	}
	
}
