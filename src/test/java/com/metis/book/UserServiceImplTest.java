package com.metis.book;

import com.metis.book.dto.RegisterForm;
import com.metis.book.dto.UserEditForm;
import com.metis.book.model.Cart;
import com.metis.book.model.Image;
import com.metis.book.model.PasswordResetToken;

import com.metis.book.model.VerificationToken;
import com.metis.book.model.user.Address;
import com.metis.book.model.user.Role;
import com.metis.book.model.user.RoleName;
import com.metis.book.model.user.User;

import com.metis.book.serviceImpl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.metis.book.repository.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder; // Add PasswordEncoder mock

    @Mock
    private CartReposiroty cartReposiroty;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private PasswordResetTokenRepository passwordTokenRepository;

    @Mock
    private CartReposiroty cartRepository;

    @Mock
    private VerificationTokenRepository verifyTokenRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private AddressRepository addressRepository;

    public UserServiceImplTest() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testCreateNewUserOAuth2() {
        User user = new User();
        // Set up user properties

        when(cartReposiroty.save(any())).thenReturn(new Cart());
        when(roleRepository.findByName(any())).thenReturn(new Role());
        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        User savedUser = userService.createNewUserOAuth2(user);

        assertNotNull(savedUser);

    }

    @Test
    public void testGeneratePasswordTokenByUser() {
        User user = new User();
        // Set up user properties

        when(passwordTokenRepository.save(any())).thenReturn(new PasswordResetToken(UUID.randomUUID().toString(), user));

        PasswordResetToken token = userService.generatePasswordTokenByUser(user);

        assertNotNull(token);

    }

    @Test
    public void testExistsByUsername() {
        // Given
        when(userRepository.existsByUsername(any())).thenReturn(true);

        // When
        boolean exists = userService.existsByUsername("username");

        // Then
        assertTrue(exists);

    }

    @Test
    public void testExistsByEmail() {
        // Given
        when(userRepository.existsByEmail(any())).thenReturn(true);

        // When
        boolean exists = userService.existsByEmail("email");

        // Then
        assertTrue(exists);

    }

    @Test
    public void testGetAllUser() {
        // Given
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);

        // When
        List<User> users = userService.getAllUser();

        // Then
        assertNotNull(users);
        assertEquals(1, users.size());

    }

    @Test
    public void testGetUserById() {
        // Given
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        User foundUser = userService.getUserById(userId);

        // Then
        assertNotNull(foundUser);

    }
    @Test
    public void testUpdateProfileForAdmin() {
        // Given
        User user = new User();
        user.setId(1L); // Set an ID
        List<Address> addresses = new ArrayList<>();
        Address address = new Address();
        address.setIsPrimary(true); // Assuming it's the primary address
        addresses.add(address);
        user.setAddresses(addresses);
        if (user.getRoles() == null) {
            user.setRoles(new ArrayList<>());
        }
        UserEditForm userEditForm = new UserEditForm();
        userEditForm.setId("1");
        userEditForm.setDistrict("New District");
        userEditForm.setSubDistrict("New Sub-District");
        userEditForm.setProvince("New Province");
        userEditForm.setStreet("New Street");
        userEditForm.setPhoneNumber("1234567890");
        userEditForm.setFullAddress("New Full Address");
        userEditForm.setFirstName("New First Name");
        userEditForm.setLastName("New Last Name");
        userEditForm.setGender("1"); // Assuming gender 1 is male, 0 is female
        userEditForm.setBirthday("1990-01-01");
        userEditForm.setEnabled("1"); // Enabled status
        userEditForm.setRole("3"); // Upgrade to staff role

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(roleRepository.findByName(any())).thenReturn(new Role());

        // When
        userService.updateProfileForAdmin(userEditForm);

        // Then
        verify(userRepository, times(1)).findById(anyLong());
        verify(roleRepository, times(1)).findByName(any());
        verify(userRepository, times(1)).save(any());
        assertTrue(user.getEnabled());
        assertFalse(user.getAddresses().isEmpty());

        // Ensure the address has been updated properly
        Address updatedAddress = user.getAddresses().get(0);
        assertNotNull(updatedAddress); // Check if the address exists


        assertEquals(1, user.getRoles().size());
    }

    // Example test structure for getUsernameById(Long userId)
    @Test
    public void testGetUsernameById_ExistingUser_ReturnsUsername() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setUsername("username");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(existingUser));

        // Invoke the method
        String username = userService.getUsernameById(1L);

        // Assertions
        assertEquals("username", username);
        // Verify repository method invocation
        verify(userRepository, times(1)).findById(anyLong());

    }

    @Test
    public void testGetUserByVerificationToken_ValidToken_ReturnsUser() {
        // Given
        String validToken = "validToken";
        VerificationToken verificationToken = Mockito.mock(VerificationToken.class);
        User expectedUser = new User();

        when(verifyTokenRepository.findByToken(validToken)).thenReturn(verificationToken);
        when(verificationToken.getUser()).thenReturn(expectedUser);

        // When
        User user = userService.getUserByVerificationToken(validToken);

        // Then
        assertNotNull(user);
        assertEquals(expectedUser, user);

    }



    // Test for scenario where token doesn't exist
    @Test
    public void testGetUserByVerificationToken_InvalidToken_ReturnsNull() {
        // Given
        String invalidToken = "invalidToken";

        VerificationToken verificationToken = mock(VerificationToken.class); // Mock VerificationToken
        when(verifyTokenRepository.findByToken(invalidToken)).thenReturn(verificationToken);
        when(verificationToken.getUser()).thenReturn(null); // Return null when getUser() is called

        // When
        User user = userService.getUserByVerificationToken(invalidToken);

        // Then
        assertNull(user);
        // Add more specific assertions if needed
    }
    @Test
    public void testCreateNewUserForAdmin() {
        // Mock RegisterForm
        RegisterForm registerRequest = mock(RegisterForm.class);
        when(registerRequest.getUsername()).thenReturn("username");
        when(registerRequest.getBirthday()).thenReturn("2002-06-06"); // or any non-null value
        when(registerRequest.getGender()).thenReturn("1"); // or any default non-null string
        // Set other necessary fields as needed for testing

        // Call the method
        User createdUser = userService.createNewUserForAdmin(registerRequest);

        // Assert
        assertNull(createdUser);

    }

}
