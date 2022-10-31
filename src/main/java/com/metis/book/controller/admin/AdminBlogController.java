package com.metis.book.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
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
		mav.setViewName("/admin/blog/form-insert-blog.html");
		return mav;
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
