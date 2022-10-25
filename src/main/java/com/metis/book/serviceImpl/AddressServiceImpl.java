package com.metis.book.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.model.user.Address;
import com.metis.book.model.user.User;
import com.metis.book.repository.AddressRepository;
import com.metis.book.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public List<Address> getAddressByUser(User user) {
		
		List<Address> addresses = addressRepository.findByUser(user);
		return addresses;
	}

}
