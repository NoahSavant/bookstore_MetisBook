package com.metis.book.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.dto.RegisterForm;
import com.metis.book.dto.UserEditForm;
import com.metis.book.dto.UserForm;
import com.metis.book.model.user.Address;
import com.metis.book.model.user.User;
import com.metis.book.service.IAddressService;
import com.metis.book.service.IUserService;
import com.metis.book.utils.ConstraintUltils;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/user")
@Slf4j
public class AdminUserController {

	@Autowired
	IUserService userService;

	@Autowired
	IAddressService addressService;

	@GetMapping
	public ModelAndView viewAdminUserPage(ModelAndView mav) {

		List<UserForm> users = getUsers();
		mav.addObject("users", users);
		mav.setViewName("admin/user/user.html");
		return mav;
	}

	@GetMapping("/insert")
	public ModelAndView viewInsertPage(ModelAndView mav) {

		mav.addObject("user", new RegisterForm());
		mav.setViewName("admin/user/formAddUser.html");
		return mav;
	}

	@PostMapping("/insert")
	public ModelAndView createNewUser(@Valid @ModelAttribute("user") RegisterForm registerRequest, BindingResult result,
			ModelAndView mav) throws IOException {
		// Check constraint
		mav = checkViolation(result, registerRequest);
		if (!mav.isEmpty()) {
			return mav;
		}

		User userSaved = userService.createNewUserForAdmin(registerRequest);
		userService.updateImageForAdmin(registerRequest.getFile(), userSaved.getId());
		// not done yet

		mav.setViewName("redirect:/admin/user");
		return mav;
	}

	@GetMapping("/edit")
	public ModelAndView viewEditUserPage(
			ModelAndView mav,
			@RequestParam("userId") String userId) {
		
		User user = userService.getUserById(Long.parseLong(userId));
		List<Address> addresses = addressService.getAddressByUser(user);
		Address address = new Address();
		for (Address add : addresses) {
			if(add.getIsPrimary()) {
				address = add;
			}
		}
		
		UserEditForm userEditForm = new UserEditForm();
		userEditForm.convert(user,address);
		mav.addObject("user",userEditForm);
		mav.setViewName("admin/user/formEditUser.html");
		return mav;
	}
	
	@PostMapping("/edit")
	public ModelAndView editUser(
			ModelAndView mav,
			@ModelAttribute("user") UserEditForm userEditForm) {
		userService.updateProfileForAdmin(userEditForm);
		mav.setViewName("redirect:/admin/user");
		return mav;
	}
	
	@GetMapping("/address")
	public ModelAndView viewAddressPage(
			ModelAndView mav,
			@RequestParam("userId") String userId) {
		User user = userService.getUserById(Long.parseLong(userId));
		List<Address> addresses = addressService.getAddressByUser(user);
		for (Address address : addresses) {
			address.setFullAddress(address.fetchFullAddress());
		}
		mav.addObject("userId",userId);
		mav.addObject("addresses",addresses);
		mav.setViewName("admin/user/address.html");
		return mav;
	}
	
	@GetMapping("/address-detail")
	public ModelAndView viewAddressDetailPage(
			ModelAndView mav,
			@RequestParam("id") String addressId,
			@RequestParam("userId") String userId) {
		
		Address address = addressService.getAddressById(Long.parseLong(addressId));
		mav.addObject("userId",userId);
		mav.addObject("address",address);
		mav.setViewName("admin/user/formEditAddress.html");
		return mav;
	}
	
	@PostMapping("/address")
	public ModelAndView editAddress(
			ModelAndView mav,
			@RequestParam("userId") String userId,
			@ModelAttribute("address") Address address) {
		
		User user = userService.getUserById(Long.parseLong(userId));
		addressService.updateAddress(address,user);
		mav.setViewName("redirect:/admin/user/address?userId="+userId);
		return mav;
	}
	
	@PostMapping("/upload-image")
	public ModelAndView uploadImage(
			ModelAndView mav,
			@RequestParam("image") MultipartFile file,
			@RequestParam("userId") String userId) throws IOException {
		userService.uploadImageForAdmin(file,userId);
		mav.setViewName("redirect:/admin/user/edit?userId="+userId);
		return mav;
	}
	
	@PostMapping("change-password")
	public ModelAndView changePassword(
			ModelAndView mav,
			@RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPassword,
			@RequestParam("userId") String userId) throws IOException {
	
		Map<String, String> authenErrors = new HashMap<>();
		if (!password.equals(confirmPassword)) {
			authenErrors.put("passwordNotMatch", "Mật khẩu nhập lại không khớp");
			mav.addObject("authenErrors", authenErrors);
		}
		userService.updatePasswordForAdmin(userId,password);
		mav.setViewName("redirect:/admin/user/edit?userId="+userId);
		return mav;
	}
	private List<UserForm> getUsers() {
		List<UserForm> list = new ArrayList<>();
		List<User> users = userService.getAllUser();
		for (User u : users) {
			UserForm userForm = new UserForm();
			userForm.convert(u);
			updateAudit(u, userForm);
			updateAddresses(u, userForm);
			list.add(userForm);
		}
		return list;
	}

	private void updateAudit(User user, UserForm userForm) {
		// Check audit
		if (user.getCreateBy() != null) {
			String createUser = userService.getUsernameById(user.getCreateBy());
			userForm.setCreateBy(createUser);
		}
		if (user.getUpdateBy() != null) {
			String updateUser = userService.getUsernameById(user.getUpdateBy());
			userForm.setLastUpdateBy(updateUser);
		}
		if (user.getCreatedAt() != null) {
			userForm.setCreateDate(user.getCreatedAt().toString());
		}
		if (user.getUpdatedAt() != null) {
			userForm.setLastUpdateDate(user.getUpdatedAt().toString());
		}
	}

	private void updateAddresses(User user, UserForm userForm) {

		List<Address> addresses = addressService.getAddressByUser(user);
		List<String> list = new ArrayList<>();
		for (Address address : addresses) {
			list.add(address.getFullAddress());
			if (address.getIsPrimary()) {
				userForm.setPrimaryAddress(address.fetchFullAddress());
			}
		}
		user.setAddresses(addresses);
	}

	private ModelAndView checkViolation(BindingResult result, RegisterForm registerRequest) {

		ModelAndView mav = new ModelAndView();
		// Check constraint on info
		if (result.hasErrors()) {
			mav.setViewName("admin/user/formAddUser.html");
			return mav;
		}

		Map<String, String> authenErrors = getAuthenError(registerRequest);

		// Check constraint on data - duplicate, not match,...
		if (authenErrors.size() > 0) {
			mav.addObject("authenErrors", authenErrors);
			mav.setViewName("admin/user/formAddUser.html");
			return mav;
		}
		return mav;
	}

	private HashMap<String, String> getAuthenError(RegisterForm registerRequest) {

		HashMap<String, String> errors = new HashMap<>();

		if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
			errors.put("passwordNotMatch", "Mật khẩu nhập lại không khớp");
		}
		if (isExistByUsername(registerRequest.getUsername())) {
			errors.put("existByUsername", "Tên đăng nhập đã tồn tại");
		}
		if (ConstraintUltils.isContainSpecialChar(registerRequest.getUsername())) {
			errors.put("usernameSpecial", "Tên đăng nhập không được phép chứa ký tự đặc biệt");
		}
		if (ConstraintUltils.isContainSpecialChar(registerRequest.getFirstName())) {
			errors.put("firstNameSpecial", "Tên không được phép chứa ký tự đặc biệt");
		}
		if (ConstraintUltils.isContainSpecialChar(registerRequest.getLastName())) {
			errors.put("lastNameSpecial", "Họ không được phép chứa ký tự đặc biệt");
		}
		if (isExistByEmail(registerRequest.getEmail())) {
			errors.put("existByEmail", "Email đã tồn tại");
		}

		log.error(errors.toString());
		return errors;
	}

	private Boolean isExistByEmail(String email) {
		if (userService.existsByEmail(email)) {
			return true;
		}
		return false;
	}

	private Boolean isExistByUsername(String username) {
		if (userService.existsByUsername(username)) {
			return true;
		}
		return false;
	}

}
