package com.metis.book.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.model.Contact;
import com.metis.book.repository.ContactRepository;
import com.metis.book.service.IContactService;

@Service
public class ContactServiceImpl implements IContactService {

	@Autowired
	ContactRepository contactRepository;
	
	@Override
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

}
