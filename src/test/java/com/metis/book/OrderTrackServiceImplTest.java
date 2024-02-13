package com.metis.book;
import com.metis.book.model.order.OrderTrack;
import com.metis.book.serviceImpl.OrderTrackServiceImpl;
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
import java.util.List;
import com.metis.book.repository.*;
import org.springframework.boot.test.context.SpringBootTest;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
public class OrderTrackServiceImplTest {

    @Mock
    private OrderTrackRepository orderTrackRepository;

    @InjectMocks
    private OrderTrackServiceImpl orderTrackService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllOrderTrack() {
        // Arrange
        List<OrderTrack> mockOrderTracks = new ArrayList<>();
        // Add mock OrderTracks with necessary properties
        mockOrderTracks.add(new OrderTrack());
        mockOrderTracks.add(new OrderTrack());

        when(orderTrackRepository.findAll()).thenReturn(mockOrderTracks);

        // Act
        List<OrderTrack> resultOrderTracks = orderTrackService.getAllOrderTrack();

        // Assert
        assertEquals(mockOrderTracks.size(), resultOrderTracks.size());
        verify(orderTrackRepository, times(1)).findAll();
    }


}
