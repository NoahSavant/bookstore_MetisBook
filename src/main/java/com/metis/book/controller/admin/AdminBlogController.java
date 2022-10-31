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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.metis.book.dto.BlogForm;
import com.metis.book.model.Blog;
import com.metis.book.service.IBlogService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/blog")
@Slf4j
public class AdminBlogController {

	@Autowired
	IBlogService blogService;

	@GetMapping
	public ModelAndView viewAdminBlogPage(ModelAndView mav) throws JsonProcessingException {
		List<Blog> blogs = blogService.getAllBlogs();
		mav.addObject("blogs",blogs);
		mav.setViewName("/admin/blog/blog.html");
		return mav;
	}

	@GetMapping("/insert")
	public ModelAndView viewInsertBlogPage(ModelAndView mav) {
		mav.addObject("blogForm", new BlogForm());
		mav.setViewName("/admin/blog/formAddBlog.html");
		return mav;
	}
	
	@PostMapping("/insert")
	public ModelAndView createNewBlog(
			ModelAndView mav,
			@ModelAttribute("blogForm") BlogForm blogForm) {
		log.info(blogForm.getContent());
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
		mav.addObject("blogForm",blogForm);
		mav.setViewName("/admin/blog/formEditBlog.html");
		return mav;
	}
	
	@PostMapping("/edit")
	public ModelAndView updateBlog(
			ModelAndView mav,
			@ModelAttribute("blogForm") BlogForm blogForm) {

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
		blogForm.setContent(blog.getContent());
		return blogForm;
	}
	
	@DeleteMapping("/delete")
	public ModelAndView deleteBlog(
			ModelAndView mav,
			@RequestParam("blogId") String blogId) {		
		blogService.deleteById(Long.parseLong(blogId));
		mav.setViewName("/admin/blog/blog.html");
		return mav;
	}
}
