package com.metis.book.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.model.Blog;
import com.metis.book.service.IBlogService;
import com.metis.book.service.IUserService;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	IBlogService blogService;
	
	@Autowired
	IUserService userService;
	
	@GetMapping 
	public ModelAndView viewBlogPage(ModelAndView mav) {
		List<Blog> blogs = blogService.getAllBlogs();
		updateAudit(blogs);
		mav.addObject("blogs",blogs);
		mav.setViewName("client/blog");
		return mav;
	}
	
	@GetMapping("/blog-detail")
	public ModelAndView viewBlogDetail(
			ModelAndView mav,
			@RequestParam("blogId") String blogId) {
		
		Blog blog = blogService.getById(Long.parseLong(blogId));
		updateAudit(Arrays.asList(blog));
		mav.addObject("blog",blog);
		mav.setViewName("client/blog-detail");
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
}
