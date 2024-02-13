package com.metis.book.serviceImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.metis.book.dto.CheckoutForm;
import com.metis.book.dto.ProfileForm;
import com.metis.book.dto.RegisterForm;
import com.metis.book.dto.UserEditForm;
import com.metis.book.model.Cart;
import com.metis.book.model.Image;
import com.metis.book.model.PasswordResetToken;
import com.metis.book.model.VerificationToken;
import com.metis.book.model.order.Order;
import com.metis.book.model.user.Address;
import com.metis.book.model.user.Role;
import com.metis.book.model.user.RoleName;
import com.metis.book.model.user.User;
import com.metis.book.repository.AddressRepository;
import com.metis.book.repository.CartReposiroty;
import com.metis.book.repository.ImageRepository;
import com.metis.book.repository.PasswordResetTokenRepository;
import com.metis.book.repository.RoleRepository;
import com.metis.book.repository.UserRepository;
import com.metis.book.repository.VerificationTokenRepository;
import com.metis.book.security.UserPrincipal;
import com.metis.book.service.IUserService;
import com.metis.book.utils.AppConstant;
import com.metis.book.utils.FileUploadUtils;

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
	ImageRepository imageRepository;

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

		// Create new Image
		Image imageThumbnail = new Image();
		imageThumbnail.setThumbnailName("avtThumbnail.jpg");
		imageThumbnail.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\avtThumbnail.jpg");
		imageRepository.save(imageThumbnail);

		// Create new User
		User user = User.builder().username(registerRequest.getUsername())
				.password(passwordEncoder.encode(registerRequest.getPassword())).email(registerRequest.getEmail())
				.firstName(registerRequest.getFirstName()).lastName(registerRequest.getLastName())
				.phoneNumber(registerRequest.getPhoneNumber()).image(imageThumbnail)
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

		int int_random = Math.abs(ThreadLocalRandom.current().nextInt());
		while (userRepository.existsByUsername(String.valueOf(int_random))) {
			int_random = ThreadLocalRandom.current().nextInt();
		}
		user.setUsername(String.valueOf(int_random));
		User userSaved = userRepository.save(user);
		
		return userSaved;
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
		if (Objects.isNull(passwordToken)) {
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
		if (user.isEmpty()) {
			log.error(AppConstant.USER_NOT_FOUND + id);
			return null;
		}
		return user.get();
	}

	@Override
	public void updateProfile(ProfileForm profileForm) {

		
		log.error("Go here");
		// Get user
		User user = userRepository.findByEmail(profileForm.getEmail());
		if (Objects.isNull(user)) {
			log.error(AppConstant.USER_NOT_FOUND + profileForm.getEmail());
		}
		log.error("User here");
		log.error(user.getEmail());
		// Get address
		List<Address> addresses = addressRepository.findByUser(user);
		Address address = new Address();
		
		if (addresses.size()==0 || Objects.isNull(addresses)) {
			log.error("Not found any address for this user");
			address.setIsPrimary(Boolean.TRUE);
		}else {
			for (Address addr : addresses) {
				if (addr.getIsPrimary()) {
					address = addr;
				}
			}
		}

		
		address.setDistrict(profileForm.getDistrict());
		address.setSubDistrict(profileForm.getSubDistrict());
		address.setProvince(profileForm.getProvince());
		address.setStreet(profileForm.getStreet());
		address.setRecievePhoneNumber(profileForm.getPhoneNumber());
		address.setUser(user);
		addressRepository.save(address);
		
		user.setFirstName(profileForm.getFirstName());
		user.setLastName(profileForm.getLastName());
		user.setGender(Integer.valueOf(profileForm.getGender()));
		user.setPhoneNumber(profileForm.getPhoneNumber());
		user.setBirthday(LocalDate.parse(profileForm.getBirthday()));
		userRepository.save(user);
	}

	@Override
	public void updateImage(MultipartFile file) throws IOException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		User user = userRepository.findById(userPrincipal.getId()).get();
		Path fileNameAndPath = FileUploadUtils.saveUserImage(file, user.getId());

		Image image = imageRepository.findByUser(user);

		if (Objects.isNull(image)) {
			// if user don't have any image
			Image newImage = new Image();
			newImage.setTitle(user.getId().toString() + ".png");
			newImage.setUrl(fileNameAndPath.toString());
			imageRepository.save(newImage);

			user.setImage(newImage);
			userRepository.save(user);
			userPrincipal.setImage(newImage);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			// if user already have image
			image.setTitle(user.getId().toString() + ".png");
			image.setUrl(fileNameAndPath.toString());
			imageRepository.save(image);

			// update authenticated user
			userPrincipal.setImage(image);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

	}

	@Override
	public String getUsernameById(Long userId) {
		User user = userRepository.findById(userId).get();
		if (Objects.isNull(user)) {
			log.error(AppConstant.USER_NOT_FOUND + userId);
		}
		return user.getUsername();
	}

	@Override
	public void updateCheckout(CheckoutForm checkoutForm) {
		User user = userRepository.findByEmail(checkoutForm.getEmail());
		if (Objects.isNull(user)) {
			log.error(AppConstant.USER_NOT_FOUND);
			return;
		}

		if (checkoutForm.getNewAddress() != null) {
			updateAddress(user, checkoutForm);
		}

	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	private void updateAddress(User user, CheckoutForm checkoutForm) {
		Address address = new Address();

		address.setProvince(checkoutForm.getProvince());
		address.setDistrict(checkoutForm.getDistrict());
		address.setSubDistrict(checkoutForm.getSubDistrict());
		address.setFullAddress(checkoutForm.getNewAddress());
		address.setUser(user);
		address.setStreet(checkoutForm.getStreet());
		address.setRecievePhoneNumber(checkoutForm.getRecievePhoneNumber());
		if (checkoutForm.getIsPrimary()) {
			address.setIsPrimary(true); // set primary is true
			// find all address of user in db and change primary status
			List<Address> addresses = addressRepository.findByUser(user);
			for (Address savedAddress : addresses) {
				if (savedAddress.getIsPrimary().equals(true)) {
					savedAddress.setIsPrimary(false);
					addressRepository.save(savedAddress);
					break;
				}
			}
		} else {
			address.setIsPrimary(false); // set primary is false
		}

		// save new address
		addressRepository.save(address);
	}

	@Override
	public User createNewUserForAdmin(RegisterForm registerRequest) {
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

		// Create new Image
		Image imageThumbnail = new Image();
		imageThumbnail.setThumbnailName("avtThumbnail.jpg");
		imageThumbnail.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\avtThumbnail.jpg");
		imageRepository.save(imageThumbnail);

		// Create new User
		User user = User.builder().username(registerRequest.getUsername())
				.password(passwordEncoder.encode(registerRequest.getPassword())).email(registerRequest.getEmail())
				.firstName(registerRequest.getFirstName()).lastName(registerRequest.getLastName())
				.phoneNumber(registerRequest.getPhoneNumber()).image(imageThumbnail)
				.birthday(
						registerRequest.getBirthday().isEmpty() ? null : LocalDate.parse(registerRequest.getBirthday()))
				.enabled(true).gender(Integer.parseInt(registerRequest.getGender())).addresses(null).cart(cartSaved)
				.roles(Arrays.asList(role)).build();

		User userSaved = userRepository.save(user);
		// Create new address
		Address address = new Address();
		address.setProvince(registerRequest.getProvince());
		address.setStreet(registerRequest.getStreet());
		address.setDistrict(registerRequest.getDistrict());
		address.setSubDistrict(registerRequest.getSubDistrict());
		address.setFullAddress(registerRequest.getFullAddress());
		address.setIsPrimary(true);
		address.setUser(userSaved);
		addressRepository.save(address);

		return userSaved;
	}

	@Override
	public void updateImageForAdmin(MultipartFile file, Long id) throws IOException {
		User user = userRepository.findById(id).get();
		Image image = user.getImage();
		if (!file.isEmpty()) {
			Path fileNameAndPath = FileUploadUtils.saveUserImage(file, id);
			image.setTitle(user.getId().toString() + ".png");
			image.setUrl(fileNameAndPath.toString());
			imageRepository.save(image);
		}

	}

	@Override
	public void uploadImageForAdmin(MultipartFile file, String userId) throws NumberFormatException, IOException {
		User user = userRepository.findById(Long.parseLong(userId)).get();
		Image image = user.getImage();
		if (!file.isEmpty()) {
			Path fileNameAndPath = FileUploadUtils.saveUserImage(file, Long.parseLong(userId));
			image.setTitle(userId + ".png");
			image.setUrl(fileNameAndPath.toString());
			imageRepository.save(image);
		}

	}

	@Override
	public void updateProfileForAdmin(UserEditForm userEditForm) {
		User user = userRepository.findById(Long.parseLong(userEditForm.getId())).get();
		List<Address> addresses = addressRepository.findByUser(user);
		Address address = new Address();
		for (Address addr : addresses) {
			if (addr.getIsPrimary()) {
				address = addr;
			}
		}

		address.setDistrict(userEditForm.getDistrict());
		address.setSubDistrict(userEditForm.getSubDistrict());
		address.setProvince(userEditForm.getProvince());
		address.setStreet(userEditForm.getStreet());
		address.setRecievePhoneNumber(userEditForm.getPhoneNumber());
		address.setFullAddress(userEditForm.getFullAddress());
		address.setIsPrimary(true);
		address.setUser(user);
		addressRepository.save(address);

		user.setFirstName(userEditForm.getFirstName());
		user.setLastName(userEditForm.getLastName());
		user.setGender(Integer.valueOf(userEditForm.getGender()));
		user.setPhoneNumber(userEditForm.getPhoneNumber());
		user.setBirthday(LocalDate.parse(userEditForm.getBirthday()));

		if (userEditForm.getEnabled().equals("1")) {
			user.setEnabled(true);
		} else {
			user.setEnabled(false);
		}
		log.error(userEditForm.getRole() + "aaaaa");
		int flag = 2; // user
		List<Role> roles = user.getRoles();
		for (Role role : roles) {
			if (role.getName().equals(RoleName.ADMIN)) {
				flag = 1;
				return;
			}
			if (role.getName().equals(RoleName.STAFF)) {
				flag = 3;
			}
		}
		Role roleStaff = roleRepository.findByName(RoleName.STAFF);

		// if upgrade to staff
		if (userEditForm.getRole().equals("3") && flag == 2) {
			roles.add(roleStaff);
		}
		// if remove staff
		else if (userEditForm.getRole().equals("2") && flag == 3) {
			log.error(flag + "aaaaa");
			roles.remove(roleStaff);
		}

		user.setRoles(roles);
		userRepository.save(user);

	}

	@Override
	public void updatePasswordForAdmin(String userId, String password) {
		User user = userRepository.findById(Long.parseLong(userId)).get();
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
	}

	@Override
	public User findByOrder(Order order) {
		return userRepository.findByOrders(order);
	}

}
