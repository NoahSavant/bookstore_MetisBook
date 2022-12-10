package com.metis.book.serviceImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.metis.book.dto.BlogForm;
import com.metis.book.dto.PageResponse;
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
	public List<Blog> getAllBlogs(){
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
		
		if(!blogForm.getFile().isEmpty()) {
			Path fileNameAndPath = FileUploadUtils.saveBlogImage(blogForm.getFile(),blogSaved.getId());
			Image image = new Image();
			image.setTitle(blogSaved.getId().toString()+".png");
			image.setUrl(fileNameAndPath.toString());
			Image imageSaved = imageRepository.save(image);
			blogSaved.setImage(imageSaved);
			blogRepository.save(blogSaved);
		}else {
			// Create thumbnail image 1
			Image imageThumbnail = new Image();
			imageThumbnail.setThumbnailName("BlogThumbnail.png");
			imageThumbnail.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BlogThumbnail.png");
			imageRepository.save(imageThumbnail);
			blogSaved.setImage(imageThumbnail);
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
		log.error(System.getProperty("user.dir"));
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

	@Override
	public List<Blog> getLatestBlogs() {
		List<Blog> blogs = blogRepository.findAll();
		List<Blog> lastestBlogs = new ArrayList<>();
		int index = Math.min(3, blogs.size());
		for(int i = 0; i < index; i++) {
			lastestBlogs.add(blogs.get(i));
		}
		return lastestBlogs;
	}

	@Override
	public PageResponse<Blog> getBlogByPage(int page) {
		Pageable pageable = PageRequest.of(page, 3); // 3 = size of each page
		Page<Blog> blogs = blogRepository.findAll(pageable);
		PageResponse<Blog> pageResponse = new PageResponse<>();
		pageResponse.setContent(blogs.getContent());
		pageResponse.setTotalPages(blogs.getTotalPages());
		pageResponse.setLast(blogs.isLast());
		pageResponse.setFirst(blogs.isFirst());
		return pageResponse;
	}

}
