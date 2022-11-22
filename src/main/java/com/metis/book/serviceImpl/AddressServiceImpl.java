package com.metis.book.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.model.user.Address;
import com.metis.book.model.user.User;
import com.metis.book.repository.AddressRepository;
import com.metis.book.service.IAddressService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddressServiceImpl implements IAddressService {

	@Autowired
	AddressRepository addressRepository;

	
	@Override
	public List<Address> getAddressByUser(User user) {
		
		List<Address> addresses = addressRepository.findByUser(user);
		return addresses;
	}

	@Override
	public Address getAddressById(Long addressId) {
		Address address = addressRepository.findById(addressId).get();
		if(Objects.isNull(address)) {
			log.error("Not found address");
		}
		return address;
	}

	@Override
	public void updateAddress(Address address, User user) {
		
		
		
		Address addressSaved = addressRepository.findById(address.getId()).get();
		if(Objects.isNull(addressSaved)) {
			log.error("Not found address");
		}
		
		addressSaved.setDistrict(address.getDistrict());
		addressSaved.setSubDistrict(address.getSubDistrict());
		addressSaved.setStreet(address.getStreet());
		addressSaved.setProvince(address.getProvince());
		addressSaved.setRecievePhoneNumber(address.getRecievePhoneNumber());
		addressSaved.setFullAddress(address.getFullAddress());
		
		
		if(address.getIsPrimary()) {
			 
			// find all address of user in db and change primary status
			List<Address> addresses = addressRepository.findByUser(user);
			for (Address savedAddress : addresses) {
				if(savedAddress.getIsPrimary().equals(true)) {
					savedAddress.setIsPrimary(false);
					addressRepository.save(savedAddress);
					break;
				}
			}
			// set primary is true
			addressSaved.setIsPrimary(true);
		}else {
			addressSaved.setIsPrimary(false); // set primary is false
		}
		
		
		addressRepository.save(addressSaved);
	}

}
