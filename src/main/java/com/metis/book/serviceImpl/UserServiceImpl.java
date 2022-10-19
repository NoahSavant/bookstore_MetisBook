package com.metis.book.serviceImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.metis.book.dto.RegisterForm;
import com.metis.book.model.Cart;
import com.metis.book.model.VerificationToken;
import com.metis.book.model.user.Role;
import com.metis.book.model.user.RoleName;
import com.metis.book.model.user.User;
import com.metis.book.repository.CartReposiroty;
import com.metis.book.repository.RoleRepository;
import com.metis.book.repository.UserRepository;
import com.metis.book.repository.VerificationTokenRepository;
import com.metis.book.service.IUserService;
import com.metis.book.utils.AppConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CartReposiroty cartReposiroty;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	VerificationTokenRepository tokenRepository;

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public User createNewUser(RegisterForm registerRequest) {

		// Create new Cart for user
		Cart cart = new Cart();
		cart.setUser(null);
		Cart cartSaved = cartReposiroty.save(cart);

		log.info("In create new User");
		// get Role user
		Role role = roleRepository.findByName(RoleName.USER);
		if (Objects.isNull(role)) {
			log.error(AppConstant.ROLE_NOT_FOUND + "USER");
		}
		log.info(role.getName().toString());
		// Create new User
		User user = User.builder().username(registerRequest.getUsername())
				.password(passwordEncoder.encode(registerRequest.getPassword())).email(registerRequest.getEmail())
				.firstName(registerRequest.getFirstName()).lastName(registerRequest.getLastName())
				.phoneNumber(registerRequest.getPhoneNumber())
				.birthday(
						registerRequest.getBirthday().isEmpty() ? null : LocalDate.parse(registerRequest.getBirthday()))
				.enabled(false) // true when click on verification link
				.gender(Integer.parseInt(registerRequest.getGender())).addresses(null).cart(cartSaved)
				.roles(Arrays.asList(role)).build();
		return userRepository.save(user);
	}

	@Override
	public void createVerificationTokenForUser(User user, String token) {
		VerificationToken verificationToken = new VerificationToken(token, user);
		log.info("In createVerificationTokenForUser");
		tokenRepository.save(verificationToken);
		log.info("After createVerificationTokenForUser");

	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);

	}

	@Override
	public VerificationToken generateNewVerificationToken(String existingToken) {
		VerificationToken token = tokenRepository.findByToken(existingToken);
		token.updateToken(UUID.randomUUID().toString());
		token = tokenRepository.save(token);
		return token;
	}

	@Override
	public User getUserByToken(String token) {
		VerificationToken verificationToken = tokenRepository.findByToken(token);
		return verificationToken.getUser();
	}

	@Override
	public VerificationToken generateTokenById(Long userId) {
		
		User user = userRepository.findById(userId).get();
		VerificationToken token = tokenRepository.findByUser(user);
		token.updateToken(UUID.randomUUID().toString());
		token = tokenRepository.save(token);
		return token;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User createNewUserOAuth2(User user) {
		
		// Create new Cart for user
		Cart cart = new Cart();
		cart.setUser(null);
		Cart cartSaved = cartReposiroty.save(cart);

		// get Role user
		Role role = roleRepository.findByName(RoleName.USER);
		if (Objects.isNull(role)) {
			log.error(AppConstant.ROLE_NOT_FOUND + "USER");
		}
		
		user.setCart(cartSaved);
		user.setRoles(Arrays.asList(role));
		
		return userRepository.save(user);
	}


}
