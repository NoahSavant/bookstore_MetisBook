package com.metis.book;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.metis.book.serviceImpl.BookRequestImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.metis.book.model.BookRequest;
import com.metis.book.repository.BookRequestRepository;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
public class BookRequestImplTest {

    @Mock
    private BookRequestRepository bookRequestRepository;

    @InjectMocks
    private BookRequestImpl bookRequestService;

    @Test
    public void testGetAllRequest() {
        // Mock data
        BookRequest request1 = new BookRequest();
        request1.setId(1L);
        request1.setName("Nguyen Van Thai");

        BookRequest request2 = new BookRequest();
        request2.setId(2L);
        request2.setName("Nguyen Tien Hung");

        when(bookRequestRepository.findAll()).thenReturn(Arrays.asList(request1, request2));

        // Perform the test
        List<BookRequest> result = bookRequestService.getAllRequest();
        assertEquals(2, result.size());
        assertEquals("Nguyen Van Thai", result.get(0).getName());
        assertEquals("Nguyen Tien Hung", result.get(1).getName());
    }

    @Test
    public void testInsertBookRequest() {
        // Mock data
        BookRequest request = new BookRequest();
        request.setId(1L);
        request.setName("Nguyen Van Thai");

        // Perform the test
        bookRequestService.insertBookRequest(request);

        // Verify that the save method was called with the correct BookRequest
        verify(bookRequestRepository, times(1)).save(request);
    }

    // Other test methods (deleteById, getById, editRequest) go here
}
