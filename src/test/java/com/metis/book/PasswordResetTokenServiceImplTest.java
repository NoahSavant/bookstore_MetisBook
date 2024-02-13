package com.metis.book;
import com.metis.book.model.PasswordResetToken;
import com.metis.book.model.user.User;
import com.metis.book.serviceImpl.PasswordResetTokenServiceImpl;
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
import com.metis.book.repository.*;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
public class PasswordResetTokenServiceImplTest {

    @Mock
    private PasswordResetTokenRepository passwordTokenRepository;

    @InjectMocks
    private PasswordResetTokenServiceImpl passwordResetTokenService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPasswordTokenByUser() {
        // Arrange
        User user = new User(); // create a mock user

        PasswordResetToken mockToken = new PasswordResetToken(); // create a mock token
        // Set up properties for the mock token if needed

        when(passwordTokenRepository.findByUser(user)).thenReturn(mockToken);

        // Act
        PasswordResetToken resultToken = passwordResetTokenService.getPasswordTokenByUser(user);

        // Assert
        assertEquals(mockToken, resultToken);
        verify(passwordTokenRepository, times(1)).findByUser(user);
    }

    // Additional test methods for other functionalities in PasswordResetTokenServiceImpl
}
