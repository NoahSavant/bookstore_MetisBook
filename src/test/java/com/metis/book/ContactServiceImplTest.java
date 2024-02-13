package com.metis.book;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.metis.book.serviceImpl.ContactServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.metis.book.model.Contact;
import com.metis.book.repository.ContactRepository;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
public class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    @Test
    public void testGetAllContacts() {
        // Mock data
        Contact contact1 = new Contact();
        contact1.setId(1L);
        contact1.setName("Tran Cong Phuong");

        Contact contact2 = new Contact();
        contact2.setId(2L);
        contact2.setName("Tran Thanh Binh");

        when(contactRepository.findAll()).thenReturn(Arrays.asList(contact1, contact2));

        // Perform the test
        List<Contact> result = contactService.getAllContacts();
        assertEquals(2, result.size());
        assertEquals("Tran Cong Phuong", result.get(0).getName());
        assertEquals("Tran Thanh Binh", result.get(1).getName());
    }

    @Test
    public void testInsertContact() {
        // Mock data
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setName("Tran Cong Phuong");

        // Perform the test
        contactService.insertContact(contact);

        // Verify that the save method was called with the correct Contact
        verify(contactRepository, times(1)).save(contact);
    }

    @Test
    public void testDeleteById() {
        // Mock data
        long contactId = 1L;

        // Perform the test
        contactService.deleteById(contactId);

        // Verify that the deleteById method was called with the correct contactId
        verify(contactRepository, times(1)).deleteById(contactId);
    }

    @Test
    public void testGetById() {
        // Mock data
        long contactId = 1L;
        Contact contact = new Contact();
        contact.setId(contactId);
        contact.setName("Tran Cong Phuong");

        when(contactRepository.findById(contactId)).thenReturn(Optional.of(contact));

        // Perform the test
        Contact result = contactService.getById(contactId);
        assertNotNull(result);
        assertEquals("Tran Cong Phuong", result.getName());
    }

    @Test
    public void testEditContact() {
        // Mock data
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setName("Tran Cong Phuong");

        Contact existingContact = new Contact();
        existingContact.setId(1L);
        existingContact.setName("Tran Thanh Binh");

        when(contactRepository.findById(1L)).thenReturn(Optional.of(existingContact));

        // Perform the test
        contactService.editContact(contact);

        // Verify that the save method was called with the correct Contact
        verify(contactRepository, times(1)).save(existingContact);
        assertEquals("Tran Cong Phuong", existingContact.getName());
    }
}
