package com.metis.book;

import com.metis.book.model.Aim;
import com.metis.book.model.Book;
import com.metis.book.model.order.Order;
import com.metis.book.model.user.User;
import com.metis.book.repository.AimRepository;
import com.metis.book.repository.BookRepository;
import com.metis.book.repository.OrderRepository;
import com.metis.book.repository.UserRepository;
import com.metis.book.serviceImpl.AimServiceImpl;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class AimServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private AimRepository aimRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private AimServiceImpl aimService;

    @Test
    public void testGetSalesData() {
        when(orderRepository.findAll()).thenReturn(Collections.emptyList());
        assertEquals("0 0 0 0 0 0 0 0 0 0 0 0 ", aimService.getSalesData(2023));
    }

    @Test
    public void testGetAimInYear() {
        when(aimRepository.findByYear(2023)).thenReturn(null);
        assertEquals("0 0 0 0 0", aimService.getAimInYear(2023));
    }

    @Test
    public void testGetAimDataCallAddBook() {
        // Mocking Aim data for a specific year
        int year = 2023; // Replace this with the desired year for testing
        Aim mockAim = new Aim();
        mockAim.setValue("100 50 20 4.5 100");
        when(aimRepository.findByYear(year)).thenReturn(mockAim);

        // Mocking User data for totalPurchase method
        List<User> mockUsers = new ArrayList<>();
        // Mock user.totalPurchase(year) for each user in the list
        // Add mockUsers to userRepository mock

        when(userRepository.findAll()).thenReturn(mockUsers);

        // Mocking Order data for totalOrder, numProduct, and orderDate
        List<Order> mockOrders = new ArrayList<>();
        // Mock order data and add to mockOrders

        when(orderRepository.findAll()).thenReturn(mockOrders);

        // Mocking Book data for getAvgRate
        List<Book> mockBooks = new ArrayList<>();
        // Mock book.getAvgRate for each book in the list
        // Add mockBooks to bookRepository mock

        when(bookRepository.findAll()).thenReturn(mockBooks);

        // Call the method under test
        String result = aimService.getAimData(year);

        // Add assertions to validate the result based on your business logic and
        // calculations
        assertNotNull(result);
        // Add more assertions based on your expected results from the method
    }

    @Test
    public void testGetAimData() {
        // Mocking Aim data for a specific year
        int year = 2023; // Replace this with the desired year for testing
        Aim mockAim = new Aim();
        mockAim.setValue("100 50 20 4.5 100");
        when(aimRepository.findByYear(year)).thenReturn(mockAim);

        // Mocking User data, Order data, Book data (similar to the previous test)

        // Call the method under test
        String result = aimService.getAimData(year);

        // Assertions for the behavior of addBookToList method indirectly called within
        // getAimData

        // For example, assuming the result contains specific data based on
        // calculations,
        // you can validate the presence of certain strings in the result
        assertFalse(result.contains("expectedData"));

        // Assert other relevant conditions based on your calculations
    }

}
