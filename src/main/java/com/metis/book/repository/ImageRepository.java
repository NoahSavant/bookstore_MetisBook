package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>  {

}
