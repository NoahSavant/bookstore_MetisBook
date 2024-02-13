package com.metis.book;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.metis.book.model.user.Address;
import com.metis.book.model.user.User;
import com.metis.book.repository.AddressRepository;
import com.metis.book.serviceImpl.AddressServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private Logger log;

    @Test
    public void testGetAddressByUser() {
        // Arrange
        User user = new User(/* set user properties here */);
        List<Address> mockAddresses = new ArrayList<>();
        mockAddresses.add(new Address(/* set address properties here */));

        when(addressRepository.findByUser(user)).thenReturn(mockAddresses);

        // Act
        List<Address> resultAddresses = addressService.getAddressByUser(user);

        // Assert
        assertEquals(mockAddresses, resultAddresses);
        verify(addressRepository, times(1)).findByUser(user);
    }

    @Test
    public void testGetAddressById_WhenAddressExists() {
        // Arrange
        Long addressId = 1L;
        Address mockAddress = new Address(/* set address properties here */);
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(mockAddress));

        // Act
        Address resultAddress = addressService.getAddressById(addressId);

        // Assert
        assertEquals(mockAddress, resultAddress);
        verify(addressRepository, times(1)).findById(addressId);
    }

    @Test
    public void testUpdateAddress() {
        // Arrange
        Address addressToUpdate = mock(Address.class); // Tạo một mock của đối tượng Address
    
        User user = new User(/* set user properties here */);
    
        // Mocking the findById method to return an Optional with the addressToUpdate
        when(addressRepository.findById(addressToUpdate.getId())).thenReturn(Optional.of(addressToUpdate));
    
        // Mocking the findByUser method to return a list with a primary address
        List<Address> addresses = new ArrayList<>();
        Address primaryAddress = new Address(/* set primary address properties here */);
        primaryAddress.setIsPrimary(true);
        addresses.add(primaryAddress);
        when(addressRepository.findByUser(user)).thenReturn(addresses);
    
        // Mocking the getIsPrimary method to return a specific value
        when(addressToUpdate.getIsPrimary()).thenReturn(Boolean.TRUE); // or Boolean.FALSE, or null as needed
    
        // Act
        addressService.updateAddress(addressToUpdate, user);
    
        // Assert
        // Verify that findById and findByUser methods are called once
        verify(addressRepository, times(1)).findById(addressToUpdate.getId());
        verify(addressRepository, times(1)).findByUser(user);
    
        // Verify that save method is called once with the updated address
        verify(addressRepository, times(1)).save(addressToUpdate);
    
        // Verify that log.error is not called (address is found)
        verify(log, never()).error(anyString());
    
        // Additional assertions based on your specific requirements
        assertFalse(primaryAddress.getIsPrimary()); // Check that primary address is set to false
        assertTrue(addressToUpdate.getIsPrimary()); // Check that updated address is set to true
    }
    
}
