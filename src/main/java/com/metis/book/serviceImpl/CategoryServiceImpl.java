package com.metis.book.serviceImpl;

import java.util.List;

import javax.validation.Valid;

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
	
	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public void insert(@Valid CategoryForm categoryForm) {
		Category category = new Category();
		category.setName(categoryForm.getName());
		categoryRepository.save(category);
	}
	
}
