package com.metis.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.user.Address;
import com.metis.book.model.user.User;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	List<Address> findByUser(User user);

}
