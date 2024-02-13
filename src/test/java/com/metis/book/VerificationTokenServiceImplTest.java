package com.metis.book;
import com.metis.book.model.VerificationToken;
import com.metis.book.model.user.User;
import com.metis.book.serviceImpl.VerificationTokenServiceImpl;
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
import org.springframework.mail.javamail.JavaMailSender;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
public class VerificationTokenServiceImplTest {


    @Mock
    private JavaMailSender mailSender;

    @Mock
    private VerificationTokenRepository tokenRepository;

    @InjectMocks
    private VerificationTokenServiceImpl verificationTokenService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetVerificationToken() {
        // Arrange
        String token = "someToken";
        VerificationToken mockToken = new VerificationToken(); // create a mock token

        when(tokenRepository.findByToken(token)).thenReturn(mockToken);

        // Act
        VerificationToken resultToken = verificationTokenService.getVerificationToken(token);

        // Assert
        assertEquals(mockToken, resultToken);
        verify(tokenRepository, times(1)).findByToken(token);
    }

    @Test
    public void testGetTokenByUser() {
        // Arrange
        User savedUser = new User(); // create a mock user
        VerificationToken mockToken = new VerificationToken(); // create a mock token
        // Set up properties for the mock token if needed

        when(tokenRepository.getTokenByUser(savedUser)).thenReturn(mockToken);

        // Act
        VerificationToken resultToken = verificationTokenService.getTokenByUser(savedUser);

        // Assert
        assertEquals(mockToken, resultToken);
        verify(tokenRepository, times(1)).getTokenByUser(savedUser);
    }
}
