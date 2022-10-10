package com.metis.book.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.metis.book.model.user.User;
import com.metis.book.repository.UserRepository;
import com.metis.book.security.UserPrincipal;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email or password not correct!"));
		return UserPrincipal.create(user);

	
	}

}
