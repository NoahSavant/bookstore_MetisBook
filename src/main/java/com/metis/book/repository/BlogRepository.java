package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>{

}
