package com.metis.book.serviceImpl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.metis.book.dto.RegisterForm;
import com.metis.book.model.Cart;
import com.metis.book.model.user.User;
import com.metis.book.repository.CartReposiroty;
import com.metis.book.repository.UserRepository;
import com.metis.book.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CartReposiroty cartReposiroty;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public void createNewUser(RegisterForm registerRequest) {
		
		Cart cart = new Cart();
		cart.setUser(null);
		Cart cartSaved = cartReposiroty.save(cart);

		
		User user = User.builder()
				.username(registerRequest.getUsername())
				.password(passwordEncoder.encode(registerRequest.getPassword()))
				.email(registerRequest.getEmail())
				.firstName(registerRequest.getFirstName())
				.lastName(registerRequest.getLastName())
				.phoneNumber(registerRequest.getPhoneNumber())
				.birthday(registerRequest.getBirthday().isEmpty() ? null : LocalDate.parse(registerRequest.getBirthday()))
				.enabled(true)
				.gender(Integer.parseInt(registerRequest.getGender()))
				.addresses(null)
				.cart(cartSaved)
				.build();
		userRepository.save(user);
	}
	
}
