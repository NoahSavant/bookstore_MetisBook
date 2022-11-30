package com.metis.book.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.model.Book;
import com.metis.book.model.Feedback;
import com.metis.book.model.user.User;
import com.metis.book.repository.BookRepository;
import com.metis.book.repository.FeedbackRepository;
import com.metis.book.repository.UserRepository;
import com.metis.book.service.IFeedbackService;

@Service
public class FeedbackServiceImpl implements IFeedbackService{

	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	@Override
	public void saveFeedback(Feedback feedback, Long userId, Long bookId) {
		Book book = bookRepository.findById(bookId).get();
		feedback.setBook(book);
		User user = userRepository.findById(userId).get();
		feedback.setUser(user);
		feedbackRepository.save(feedback);
	}
	@Override
	public List<Feedback> getFeedbacksById(Long bookId) {
		Book book = bookRepository.findById(bookId).get();
		return feedbackRepository.findByBook(book);
	}
	@Override
	public Feedback getById(Long feedbackId) {
		
		return feedbackRepository.findById(feedbackId).get();
	}
	@Override
	public void deleteById(Long feedbackId) {
		feedbackRepository.deleteById(feedbackId);
		
	}

}
