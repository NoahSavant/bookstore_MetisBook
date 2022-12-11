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

import com.metis.book.dto.AuthorForm;
import com.metis.book.dto.BookForm;
import com.metis.book.dto.CategoryForm;
import com.metis.book.dto.LanguageForm;
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

	@GetMapping
	public ModelAndView bookView() {
		ModelAndView mav = new ModelAndView();
		List<BookForm> bookForms = bookService.getBookShows();
		mav.addObject("books", bookForms);
		mav.setViewName("admin/book/book");
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
		mav.setViewName("admin/book/formAddBook.html");
		return mav;
	}
	@PostMapping("/add")
	public ModelAndView insert(@Valid @ModelAttribute("bookForm") BookForm bookForm, BindingResult result) throws ParseException, IOException {
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
		mav.setViewName("admin/book/book.html");
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
		mav.setViewName("admin/book/formEditBook.html");
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
	@GetMapping("/category")
	public ModelAndView categoryView() {
		ModelAndView mav = new ModelAndView();
		List<CategoryForm> categoryForms = categoryService.getCategoryShows();
		mav.addObject("categoryForms", categoryForms);
		mav.setViewName("admin/book/category.html");
		return mav;
	}
	@GetMapping("/category/add")
	public ModelAndView viewInsertCategory() {
		ModelAndView mav = new ModelAndView();
		CategoryForm categoryForm = new CategoryForm();
		mav.addObject("category", categoryForm);
		mav.setViewName("admin/book/formAddCategory.html");
		return mav;
	}
	@PostMapping("/category/add")
	public ModelAndView insertCategory(@Valid @ModelAttribute("category") CategoryForm category, BindingResult result) throws IOException {
		ModelAndView mav = new ModelAndView();
		categoryService.insert(category);
		mav.setViewName("redirect:/admin/book/");
		return mav;
	}
	@GetMapping("/category/edit")
	public ModelAndView viewUpdateCategory(
			ModelAndView mav,
			@RequestParam("id") String id) {

		CategoryForm categoryForm = categoryService.getById(Long.parseLong(id));
		mav.addObject("category", categoryForm);
		mav.setViewName("admin/book/formEditCategory.html");
		return mav;
	}
	@PostMapping("/category/edit")
	public ModelAndView updateCategory(
			ModelAndView mav,
			@ModelAttribute("category") CategoryForm categoryForm, BindingResult result) throws ParseException, IOException {
		categoryService.updateCategory(categoryForm);
		mav.addObject("updateSucceed", true);
		mav.setViewName("redirect:/admin/book/category");
		return mav;
	}
	@GetMapping("/author")
	public ModelAndView viewAuthor() {
		ModelAndView mav = new ModelAndView();
		List<AuthorForm> authorForms = authorService.getAuthorShows();
		mav.addObject("authorForms", authorForms);
		mav.setViewName("admin/book/author.html");
		return mav;
	}
	@GetMapping("/author/add")
	public ModelAndView viewInsertAuthor() {
		ModelAndView mav = new ModelAndView();
		AuthorForm authorForm = new AuthorForm();
		mav.addObject("author", authorForm);
		mav.setViewName("admin/book/formAddAuthor.html");
		return mav;
	}
	@PostMapping("/author/add")
	public ModelAndView insertAuthor(@Valid @ModelAttribute("author") AuthorForm author, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		authorService.insert(author);
		mav.setViewName("redirect:/admin/book/author/");
		return mav;
	}
	@GetMapping("/author/edit")
	public ModelAndView viewUpdateAuthor(
			ModelAndView mav,
			@RequestParam("id") String id) {

		AuthorForm authorForm = authorService.getById(Long.parseLong(id));
		mav.addObject("author", authorForm);
		mav.setViewName("admin/book/formEditAuthor.html");
		return mav;
	}
	@PostMapping("/author/edit")
	public ModelAndView updateAuthor(
			ModelAndView mav,
			@ModelAttribute("author") AuthorForm authorForm, BindingResult result) throws ParseException, IOException {
		authorService.updateAuthor(authorForm);
		mav.addObject("updateSucceed", true);
		mav.setViewName("redirect:/admin/book/author/");
		return mav;
	}
	@GetMapping("/language")
	public ModelAndView viewLanguage() {
		ModelAndView mav = new ModelAndView();
		List<LanguageForm> languageForms = languageService.getLanguageShows();
		mav.addObject("languageForms", languageForms);
		mav.setViewName("admin/book/language.html");
		return mav;
	}
	@GetMapping("/language/add")
	public ModelAndView viewInsertLanguage() {
		ModelAndView mav = new ModelAndView();
		LanguageForm languageForm = new LanguageForm();
		mav.addObject("language", languageForm);
		mav.setViewName("admin/book/formAddLanguage.html");
		return mav;
	}
	@PostMapping("/language/add")
	public ModelAndView insertLanguage(@Valid @ModelAttribute("language") LanguageForm language, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		languageService.insert(language);
		mav.setViewName("redirect:/admin/book/");
		return mav;
	}
	@GetMapping("/language/edit")
	public ModelAndView viewUpdateLanguage(
			ModelAndView mav,
			@RequestParam("id") String id) {

		LanguageForm languageForm = languageService.getById(Long.parseLong(id));
		mav.addObject("language", languageForm);
		mav.setViewName("admin/book/formEditLanguage.html");
		return mav;
	}
	@PostMapping("/language/edit")
	public ModelAndView updateLanguage(
			ModelAndView mav,
			@ModelAttribute("language") LanguageForm languageForm, BindingResult result) throws ParseException, IOException {
		languageService.updateLanguage(languageForm);
		mav.addObject("updateSucceed", true);
		mav.setViewName("redirect:/admin/book/language/");
		return mav;
	}
}
