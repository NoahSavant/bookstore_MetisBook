package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.Image;
import com.metis.book.model.user.User;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>  {

	Image findByUser(User user);

}
