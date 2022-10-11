package com.metis.book.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.repository.UserRepository;
import com.metis.book.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	
}
