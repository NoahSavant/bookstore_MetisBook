package com.metis.book.service;

import java.util.List;

import com.metis.book.model.Feedback;

public interface IFeedbackService {

	void saveFeedback(Feedback feedback, Long userId, Long bookId);

	List<Feedback> getFeedbacksById(Long bookId);

	Feedback getById(Long feedbackId);

	void deleteById(Long feedbackId);

}
