package com.metis.book.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.metis.book.model.user.User;
import com.metis.book.repository.UserRepository;
import com.metis.book.security.UserPrincipal;
import com.metis.book.utils.AppConstant;

@Service
public class CustomUserServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email){
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(AppConstant.USER_NOT_FOUND+email));
		return UserPrincipal.create(user);

	
	}

}
