package com.metis.book.serviceImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import com.metis.book.model.Image;
import com.metis.book.repository.ImageRepository;
import com.metis.book.repository.UserRepository;
import com.metis.book.utils.AppConstant;
import com.metis.book.utils.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.dto.CategoryForm;
import com.metis.book.model.Category;
import com.metis.book.repository.CategoryRepository;
import com.metis.book.service.ICategoryService;

@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	UserRepository userRepository;
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

	@Override
	public List<CategoryForm> getCategoryShows() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryForm> categoryForms = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		for (Category category: categories) {
			CategoryForm categoryForm = new CategoryForm();
			categoryForm.setId(category.getId().toString());
			categoryForm.setName(category.getName());
			if (category.getCreateBy()!=null){
				categoryForm.setCreateBy(userRepository.findById(category.getCreateBy()).get().getUsername());
			} else {
				categoryForm.setCreateBy("");
			}
			if (category.getUpdateBy()!=null){
				categoryForm.setLastUpdateBy(userRepository.findById(category.getUpdateBy()).get().getUsername());
			} else {
				categoryForm.setLastUpdateBy("");
			}
			categoryForm.setCreateDate(formatter.format(category.getCreatedAt()));
			categoryForm.setLastUpdateDate(formatter.format(category.getUpdatedAt()));
			categoryForms.add(categoryForm);
		}
		return categoryForms;
	}

	@Override
	public CategoryForm getById(long id) {
		Category category = categoryRepository.findById(id).get();
		CategoryForm categoryForm = new CategoryForm();
		if (Objects.isNull(category)) {
			log.error(AppConstant.CATEGORY_NOT_FOUND + " " + id);
		}
		categoryForm.setId(category.getId().toString());
		categoryForm.setName(category.getName());
		return categoryForm;
	}

	@Override
	public void updateCategory(CategoryForm categoryForm) throws IOException {
		Category category = categoryRepository.findById(Long.parseLong(categoryForm.getId())).get();
		if (Objects.isNull(category)) {
			log.error(AppConstant.CATEGORY_NOT_FOUND + " " + categoryForm.getId());
		}
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
		}
	}

}
