package com.metis.book.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.dto.RegisterForm;
import com.metis.book.dto.UserForm;
import com.metis.book.model.user.Address;
import com.metis.book.model.user.User;
import com.metis.book.service.IAddressService;
import com.metis.book.service.IUserService;

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
		mav.setViewName("/admin/user/user.html");
		return mav;
	}
	
	@GetMapping("/insert")
	public ModelAndView viewInsertPage(ModelAndView mav) {

		mav.addObject("user", new RegisterForm());
		mav.setViewName("/admin/user/formAddUser.html");
		return mav;
	}
	
	@PostMapping("/insert")
	public ModelAndView createNewUser(@Valid @ModelAttribute("user") RegisterForm registerRequest,
			BindingResult result, ModelAndView mav) {

		if(result.hasErrors()) {
			mav.setViewName("/admin/user/formAddUser.html");
			return mav;
		}
		
		User savedUser = userService.createNewUser(registerRequest);
		
		// not done yet
		
		mav.setViewName("redirect:/admin/user");
		return mav;
	}
	private List<UserForm> getUsers() {
		List<UserForm> list = new ArrayList<>();
		List<User> users = userService.getAllUser();
		for (User u : users) {
			UserForm userForm = new UserForm();
			userForm.convert(u);
			updateAudit(u,userForm);
			updateAddresses(u,userForm);
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
		if(user.getCreatedAt() != null) {
			userForm.setCreateDate(user.getCreatedAt().toString());
		}
		if(user.getUpdatedAt() != null) {
			userForm.setLastUpdateDate(user.getUpdatedAt().toString());
		}
	}
	
	private void updateAddresses(User user, UserForm userForm) {
		
		List<Address> addresses = addressService.getAddressByUser(user);
		List<String> list = new ArrayList<>();
		for (Address address : addresses) {
			list.add(address.getFullAddress());
			if(address.getIsPrimary()) {
				userForm.setPrimaryAddress(address.getFullAddress());
			}
		}
		user.setAddresses(addresses);
	}

}
