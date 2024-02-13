package com.metis.book.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.metis.book.dto.BlogForm;
import com.metis.book.model.Blog;
import com.metis.book.model.Book;
import com.metis.book.service.IBlogService;
import com.metis.book.service.IBookService;
import com.metis.book.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/blog")
@Slf4j
public class AdminBlogController {

	@Autowired
	IBlogService blogService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IBookService bookService;

	
	@GetMapping
	public ModelAndView viewAdminBlogPage(ModelAndView mav) throws JsonProcessingException {
		List<Blog> blogs = blogService.getAllBlogs();
		updateAudit(blogs);
		mav.addObject("blogs",blogs);
		mav.setViewName("admin/blog/blog.html");
		return mav;
	}
	
	private void updateAudit(List<Blog> blogs) {
		for (Blog blog : blogs) {
			if(blog.getCreateBy()!=null) {
				String createUser = userService.getUsernameById(blog.getCreateBy());
				blog.setCreatedUser(createUser);
			}
			if(blog.getUpdateBy()!=null) {
				String updateUser = userService.getUsernameById(blog.getUpdateBy());
				blog.setUpdatedUser(updateUser);
			}
		}
	}
	
	@GetMapping("/insert")
	public ModelAndView viewInsertBlogPage(ModelAndView mav) {
		
		mav.addObject("blogForm", new BlogForm());
		List<Book> books = bookService.getAllBooks();
		mav.addObject("books",books);
		mav.setViewName("admin/blog/formAddBlog.html");
		return mav;
	}
	
	@PostMapping("/insert")
	public ModelAndView createNewBlog(
			ModelAndView mav,
			@ModelAttribute("blogForm") BlogForm blogForm) throws IOException {
		blogService.addBlog(blogForm);
		
		mav.setViewName("redirect:/admin/blog");
		return mav;
	}
	
	@GetMapping("/edit")
	public ModelAndView updateBlog(
			ModelAndView mav,
			@RequestParam("blogId") String blogId) {
		
		Blog blog = blogService.getById(Long.parseLong(blogId));
		BlogForm blogForm = convert(blog);
		List<Book> books = bookService.getAllBooks();
		mav.addObject("books",books);
		mav.addObject("blogForm",blogForm);
		mav.setViewName("admin/blog/formEditBlog.html");
		return mav;
	}
	
	@PostMapping("/edit")
	public ModelAndView updateBlog(
			ModelAndView mav,
			@ModelAttribute("blogForm") BlogForm blogForm) throws IOException {

		blogService.updateBlog(blogForm);

		List<Blog> blogs = blogService.getAllBlogs();
		mav.addObject("blogs",blogs);
		mav.setViewName("redirect:/admin/blog");
		return mav;
	}
	
	
	private BlogForm convert(Blog blog) {
		BlogForm blogForm = new BlogForm();
		blogForm.setId(blog.getId().toString());
		blogForm.setTitle(blog.getTitle());
		blogForm.setSubTitle(blog.getSubTitle());
		blogForm.setContent(blog.getContent());
		
		if(!blog.getBook().equals(null)) {
			blogForm.setBook(blog.getBook().getId().toString());
		}
		
		if(blog.getImage()!=null) {
			blogForm.setImageName(blog.getImage().getTitle());
		}
		return blogForm;
	}
	
	@DeleteMapping("/delete")
	public ModelAndView deleteBlog(
			ModelAndView mav,
			@RequestParam("blogId") String blogId) {		
		blogService.deleteById(Long.parseLong(blogId));
		mav.setViewName("admin/blog/blog.html");
		return mav;
	}
}
