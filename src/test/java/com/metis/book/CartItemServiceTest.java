package com.metis.book;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
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
import com.metis.book.repository.CartItemReposirory;
import com.metis.book.serviceImpl.CartItemService;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
public class CartItemServiceTest {

    @Mock
    private CartItemReposirory cartItemRepository;

    @InjectMocks
    private CartItemService cartService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Long itemIdToDelete = 1L;

        // Act
        cartService.deleteById(itemIdToDelete);

        // Assert
        verify(cartItemRepository, times(1)).deleteById(itemIdToDelete);
    }
}

