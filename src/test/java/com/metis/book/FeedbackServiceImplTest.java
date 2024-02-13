package com.metis.book;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.metis.book.serviceImpl.FeedbackServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.metis.book.model.Book;
import com.metis.book.model.Feedback;
import com.metis.book.model.user.User;
import com.metis.book.repository.BookRepository;
import com.metis.book.repository.FeedbackRepository;
import com.metis.book.repository.UserRepository;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
public class FeedbackServiceImplTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    @Test
    public void testSaveFeedback() {
        // Mock data
        Long userId = 1L;
        Long bookId = 2L;

        User user = new User();
        user.setId(userId);

        Book book = new Book();
        book.setId(bookId);

        Feedback feedback = new Feedback();
        feedback.setContent("This is a very good");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Perform the test
        feedbackService.saveFeedback(feedback, userId, bookId);

        // Verify that the save method was called with the correct Feedback
        verify(feedbackRepository, times(1)).save(feedback);
        assertEquals(book, feedback.getBook());
        assertEquals(user, feedback.getUser());
    }

    @Test
    public void testGetFeedbacksById() {
        // Mock data
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);

        Feedback feedback1 = new Feedback();
        feedback1.setId(1L);
        feedback1.setContent("it's really nice");

        Feedback feedback2 = new Feedback();
        feedback2.setId(2L);
        feedback2.setContent("I enjoyed reading it");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(feedbackRepository.findByBook(book)).thenReturn(Arrays.asList(feedback1, feedback2));

        // Perform the test
        List<Feedback> result = feedbackService.getFeedbacksById(bookId);

        assertEquals(2, result.size());
        assertEquals("it's really nice", result.get(0).getContent());
        assertEquals("I enjoyed reading it", result.get(1).getContent());
    }

    @Test
    public void testGetById() {
        // Mock data
        Long feedbackId = 1L;
        Feedback feedback = new Feedback();
        feedback.setId(feedbackId);
        feedback.setContent("This is a very good");

        when(feedbackRepository.findById(feedbackId)).thenReturn(Optional.of(feedback));

        // Perform the test
        Feedback result = feedbackService.getById(feedbackId);

        assertNotNull(result);
        assertEquals("This is a very good", result.getContent());
    }

    @Test
    public void testDeleteById() {
        // Mock data
        Long feedbackId = 1L;

        // Perform the test
        feedbackService.deleteById(feedbackId);

        // Verify that the deleteById method was called with the correct feedbackId
        verify(feedbackRepository, times(1)).deleteById(feedbackId);
    }
}
