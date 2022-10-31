package com.metis.book.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.model.Blog;
import com.metis.book.repository.BlogRepository;
import com.metis.book.service.IBlogService;

@Service
public class BlogServiceImpl implements IBlogService {

	@Autowired
	BlogRepository blogRepository;
	
	@Override
	public List<Blog> getAllBlogs() {
		return blogRepository.findAll();
	}

	@Override
	public void deleteById(Long blogId) {
		blogRepository.deleteById(blogId);
		
	}

}
