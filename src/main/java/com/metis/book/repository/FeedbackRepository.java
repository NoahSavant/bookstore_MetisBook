package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{

}
