package com.metis.book.serviceImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.validation.Valid;

import com.metis.book.model.Image;
import com.metis.book.repository.ImageRepository;
import com.metis.book.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.dto.CategoryForm;
import com.metis.book.model.Category;
import com.metis.book.repository.CategoryRepository;
import com.metis.book.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ImageRepository imageRepository;
	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public void insert(@Valid CategoryForm categoryForm) throws IOException {
		Category category = new Category();
		category.setName(categoryForm.getName());
		Category categorySaved = categoryRepository.save(category);
		if (!categoryForm.getFile().isEmpty()) {
			Path fileNameAndPath = FileUploadUtils.saveCategorryImage(categoryForm.getFile(), categorySaved.getId());
			Image image = new Image();
			image.setTitle(categorySaved.getId().toString() + ".png");
			image.setUrl(fileNameAndPath.toString());
			Image imageSaved = imageRepository.save(image);
			categorySaved.setImage(imageSaved);
			categoryRepository.save(categorySaved);
		} else {
			// Create thumbnail image 1
			Image imageThumbnail = new Image();
			imageThumbnail.setThumbnailName("BookThumbnail.png");
			imageThumbnail
					.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\BookThumbnail.png");
			imageRepository.save(imageThumbnail);
			categorySaved.setImage(imageThumbnail);
			categoryRepository.save(categorySaved);
		}
	}
	
}
