package com.metis.book;

import com.metis.book.model.Cart;
import com.metis.book.model.user.Role;
import com.metis.book.model.user.User;
import com.metis.book.repository.UserRepository;
import com.metis.book.serviceImpl.CustomUserServiceImpl;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class CustomUserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserServiceImpl customUserService;

    @Test
    public void testLoadUserByUsername_UserFound() {
        String email = "test@example.com";
        User user = new User();
        Role role = new Role();
        List<Role> roles = new ArrayList<>(); // Create a List<Role>
        roles.add(role);
        user.setId(1L);
        user.setEmail(email);
        user.setRoles(roles);
        user.setCart(new Cart());

        // Mocking behavior for userRepository.findByEmail
        when(userRepository.findByEmail(email)).thenReturn(user);

        UserDetails userDetails = customUserService.loadUserByUsername(email);

        assertNotNull(userDetails);
        assertEquals(null, userDetails.getUsername()); // Assuming email is used as the username
        // Add assertions to validate UserDetails or UserPrincipal if necessary
        verify(userRepository, times(1)).findByEmail(email);
    }
}
