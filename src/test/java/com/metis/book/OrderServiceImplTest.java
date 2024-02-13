package com.metis.book;
import com.metis.book.dto.OrderShow;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.metis.book.model.Book;
import com.metis.book.model.order.Order;
import com.metis.book.model.user.User;
import com.metis.book.repository.*;
import com.metis.book.serviceImpl.OrderServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllOrderByUser() {
        // Arrange
        User user = new User(); // Setup user with necessary properties
        List<Order> mockOrders = new ArrayList<>();
        mockOrders.add(new Order()); // Add mock Order with necessary properties

        when(orderRepository.findByUser(user)).thenReturn(mockOrders);

        // Act
        List<Order> resultOrders = orderService.getAllOrderByUser(user);

        // Assert
        assertEquals(mockOrders, resultOrders);
        verify(orderRepository, times(1)).findByUser(user);
    }


    @Test
    public void testGetOrderShows() {
        // Arrange
        User user = new User(); // Setup user with necessary properties
        List<Order> orders = new ArrayList<>();

        Order mockOrder = new Order(); // Add mock Order with necessary properties
        mockOrder.setOrderDate(new Date()); // Set a non-null order date
        orders.add(mockOrder);

        user.setOrders(orders); // Set the orders to the user

        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<OrderShow> result = orderService.getOrderShows();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(userRepository, times(1)).findAll();


    }




    @Test
    public void testGetTop3OrderByUser() {
        // Arrange
        User user = new User(); // Setup User with necessary properties
        List<Order> mockOrders = new ArrayList<>(); // Add mock Orders
        // ... populate mockOrders
        when(orderRepository.findTop3ByUser(user)).thenReturn(mockOrders);

        // Act
        List<Order> result = orderService.getTop3OrderByUser(user);

        // Assert
        assertEquals(mockOrders, result);
        verify(orderRepository, times(1)).findTop3ByUser(user);
    }


    @Test
    public void testExistByUserAndBook() {
        // Arrange
        User user = new User(); // Setup User with necessary properties
        Long bookId = 1L;
        Book book = new Book(); // Setup Book with necessary properties
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order(); // Mock Order
        // Setup order1 with necessary properties
        orders.add(order1);
        when(orderRepository.findByUser(user)).thenReturn(orders);

        // Mock the existence check for specific order-book pairs
        when(orderItemRepository.existsByBookAndOrder(book, order1)).thenReturn(true);

        // Act
        Boolean result = orderService.ExistByUserAndBook(user, bookId);

        // Assert
        assertTrue(result);
        verify(bookRepository, times(1)).findById(bookId);
        verify(orderRepository, times(1)).findByUser(user);
    }



}
