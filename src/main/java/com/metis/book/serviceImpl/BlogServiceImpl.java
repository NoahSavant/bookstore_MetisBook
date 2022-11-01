package com.metis.book.serviceImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.metis.book.dto.BlogForm;
import com.metis.book.model.Blog;
import com.metis.book.model.Image;
import com.metis.book.repository.BlogRepository;
import com.metis.book.repository.ImageRepository;
import com.metis.book.service.IBlogService;
import com.metis.book.utils.AppConstant;
import com.metis.book.utils.FileUploadUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BlogServiceImpl implements IBlogService {

	@Autowired
	BlogRepository blogRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
	@Override
	public List<Blog> getAllBlogs() {
		return blogRepository.findAll();
	}

	@Override
	public void deleteById(Long blogId) {
		blogRepository.deleteById(blogId);
		
	}

	@Override
	public void addBlog(BlogForm blogForm) throws IOException {
		
		Blog blog = new Blog();
		blog.setTitle(blogForm.getTitle());
		blog.setSubTitle(blogForm.getSubTitle());
		blog.setContent(blogForm.getContent());
		
		Blog blogSaved = blogRepository.save(blog);
		
		if(!Objects.isNull(blogForm.getFile())) {
			Path fileNameAndPath = FileUploadUtils.saveBlogImage(blogForm.getFile(),blogSaved.getId());
			Image image = new Image();
			image.setTitle(blogSaved.getId().toString()+".png");
			image.setUrl(fileNameAndPath.toString());
			Image imageSaved = imageRepository.save(image);
			blogSaved.setImage(imageSaved);
			blogRepository.save(blogSaved);
		}
		
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
	public void updateBlog(BlogForm blogForm) throws IOException {
		log.info(blogForm.getId());
		Long blogId = Long.parseLong(blogForm.getId());
		Blog blog = blogRepository.findById(blogId).get();
		if(Objects.isNull(blog)) {
			log.error(AppConstant.BLOG_NOT_FOUND+blogId);
			return;
		}
		
		blog.setTitle(blogForm.getTitle());
		blog.setSubTitle(blogForm.getSubTitle());
		blog.setContent(blogForm.getContent());
		Blog blogSaved = blogRepository.save(blog);
		
		if(!Objects.isNull(blogForm.getFile())) {
			Path fileNameAndPath = FileUploadUtils.saveBlogImage(blogForm.getFile(),blogSaved.getId());
			Image image = new Image();
			image.setTitle(blogSaved.getId().toString()+".png");
			image.setUrl(fileNameAndPath.toString());
			Image imageSaved = imageRepository.save(image);
			blogSaved.setImage(imageSaved);
			blogRepository.save(blogSaved);
		}
	}

	@Override
	public void updateImage(MultipartFile file) {
		// TODO Auto-generated method stub
		
	}

}
