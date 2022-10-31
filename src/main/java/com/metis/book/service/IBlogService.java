package com.metis.book.service;

import java.util.List;

import com.metis.book.dto.BlogForm;
import com.metis.book.model.Blog;

public interface IBlogService {

	List<Blog> getAllBlogs();

	void deleteById(Long blogId);

	void addBlog(BlogForm blogForm);

	Blog getById(Long blogId);

	void updateBlog(BlogForm blogForm);


}
