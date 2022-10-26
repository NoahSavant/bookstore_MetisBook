package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	Category findByName(String string);
    
	
}
