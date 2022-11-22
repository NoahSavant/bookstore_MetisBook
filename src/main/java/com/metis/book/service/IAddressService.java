package com.metis.book.service;

import java.util.List;

import com.metis.book.model.user.Address;
import com.metis.book.model.user.User;

public interface IAddressService {

	List<Address> getAddressByUser(User user);

	Address getAddressById(Long addressId);

	void updateAddress(Address address, User user);

}
