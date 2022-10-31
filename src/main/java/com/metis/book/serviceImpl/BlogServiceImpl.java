package com.metis.book.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.dto.BlogForm;
import com.metis.book.model.Blog;
import com.metis.book.repository.BlogRepository;
import com.metis.book.service.IBlogService;
import com.metis.book.utils.AppConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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

	@Override
	public void addBlog(BlogForm blogForm) {
		
		Blog blog = new Blog();
		blog.setTitle(blogForm.getTitle());
		blog.setContent(blogForm.getContent());
		blogRepository.save(blog);
		
	}

	@Override
	public Blog getById(Long blogId) {
		Blog blog = blogRepository.findById(blogId).get();
		if(Objects.isNull(blog)) {
			log.error(AppConstant.BLOG_NOT_FOUND+blogId);
		}
		return blog;
	}

	@Override
	public void updateBlog(BlogForm blogForm) {
		log.info(blogForm.getId());
		Long blogId = Long.parseLong(blogForm.getId());
		Blog blog = blogRepository.findById(blogId).get();
		if(Objects.isNull(blog)) {
			log.error(AppConstant.BLOG_NOT_FOUND+blogId);
			return;
		}
		
		blog.setTitle(blogForm.getTitle());
		blog.setContent(blogForm.getContent());
		blogRepository.save(blog);
	}

}
