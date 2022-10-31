package com.metis.book.service;

import java.util.List;

import com.metis.book.model.Blog;

public interface IBlogService {

	List<Blog> getAllBlogs();

	void deleteById(Long blogId);

}
