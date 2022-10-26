package com.metis.book.serviceImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.metis.book.dto.ProfileForm;
import com.metis.book.dto.RegisterForm;
import com.metis.book.model.Cart;
import com.metis.book.model.PasswordResetToken;
import com.metis.book.model.VerificationToken;
import com.metis.book.model.user.Address;
import com.metis.book.model.user.Role;
import com.metis.book.model.user.RoleName;
import com.metis.book.model.user.User;
import com.metis.book.repository.AddressRepository;
import com.metis.book.repository.CartReposiroty;
import com.metis.book.repository.PasswordResetTokenRepository;
import com.metis.book.repository.RoleRepository;
import com.metis.book.repository.UserRepository;
import com.metis.book.repository.VerificationTokenRepository;
import com.metis.book.security.UserPrincipal;
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
	AddressRepository addressRepository;
	
	@Autowired
	VerificationTokenRepository verifyTokenRepository;
	
	@Autowired
	PasswordResetTokenRepository passwordTokenRepository;

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
		verifyTokenRepository.save(verificationToken);
		log.info("After createVerificationTokenForUser");

	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);

	}

	@Override
	public VerificationToken generateNewVerificationToken(String existingToken) {
		VerificationToken token = verifyTokenRepository.findByToken(existingToken);
		token.updateToken(UUID.randomUUID().toString());
		token = verifyTokenRepository.save(token);
		return token;
	}

	@Override
	public User getUserByVerificationToken(String token) {
		VerificationToken verificationToken = verifyTokenRepository.findByToken(token);
		return verificationToken.getUser();
	}

	@Override
	public VerificationToken generateVerifyTokenById(Long userId) {
		
		User user = userRepository.findById(userId).get();
		VerificationToken token = verifyTokenRepository.findByUser(user);
		token.updateToken(UUID.randomUUID().toString());
		token = verifyTokenRepository.save(token);
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

	@Override
	public PasswordResetToken generatePasswordTokenByUser(User user) {

		final String token = UUID.randomUUID().toString();
		PasswordResetToken passwordToken = new PasswordResetToken(token, user);
		return passwordTokenRepository.save(passwordToken);
	}

	@Override
	public PasswordResetToken generateNewPasswordToken(String existingToken) {
		PasswordResetToken token = passwordTokenRepository.findByToken(existingToken);
		token.updateToken(UUID.randomUUID().toString());
		token = passwordTokenRepository.save(token);
		return token;
	}

	@Override
	public User getUserByPasswordToken(String token) {
		PasswordResetToken passwordToken = passwordTokenRepository.findByToken(token);
		if(Objects.isNull(passwordToken)) {
			return null;
		}
		return passwordToken.getUser();
	}

	@Override
	public void updatePassword(String passwordToken, String password) {
		
		User user = getUserByPasswordToken(passwordToken);
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);		
	}

	@Override
	public User getUserById(Long id) {
		
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			log.error(AppConstant.USER_NOT_FOUND+id);
			return null;
		}
		return user.get();
	}

	@Override
	public void updateProfile(ProfileForm profileForm) {
		
		// Get user
		User user = userRepository.findByEmail(profileForm.getEmail());
		if(Objects.isNull(user)) {
			log.error(AppConstant.USER_NOT_FOUND+profileForm.getEmail());
		}
		
		// Get address
		List<Address> addresses = addressRepository.findByUser(user);
		if(Objects.isNull(addresses)) {
			log.error("Not found any address for this user");
		}
		
		Address address = new Address();
		for (Address addr : addresses) {
			if (addr.getIsPrimary()) {
				address = addr;
			}
		}
		
		address.setDistrict(profileForm.getDistrict());
		address.setSubDistrict(profileForm.getSubDistrict());
		address.setProvince(profileForm.getProvince());
		address.setStreet(profileForm.getStreet());
		addressRepository.save(address);
		
		user.setFirstName(profileForm.getFirstName());
		user.setLastName(profileForm.getLastName());
		user.setGender(Integer.valueOf(profileForm.getGender()));
		user.setPhoneNumber(profileForm.getPhoneNumber());
		user.setBirthday(LocalDate.parse(profileForm.getBirthday()));
	
		userRepository.save(user);
	}
	


}
