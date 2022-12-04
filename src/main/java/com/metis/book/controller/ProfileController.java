package com.metis.book.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.dto.PageResponse;
import com.metis.book.dto.ProfileForm;
import com.metis.book.model.order.Order;
import com.metis.book.model.user.Address;
import com.metis.book.model.user.User;
import com.metis.book.security.UserPrincipal;
import com.metis.book.service.IAddressService;
import com.metis.book.service.IOrderService;
import com.metis.book.service.IUserService;
import com.metis.book.utils.ConstraintUltils;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/profile")
@Slf4j
public class ProfileController {

	@Autowired
	IAddressService addressService;

	@Autowired
	IUserService userService;
	
	@Autowired
	IOrderService orderService;

	@GetMapping
	public ModelAndView viewProfilePage(ModelAndView mav) {

		// get required objects
		mav = renderObjects();
		return mav;
	}

	@PostMapping("/upload-image")
	public ModelAndView uploadImage(
			ModelAndView mav,
			@RequestParam("image") MultipartFile file) throws IOException {
		userService.updateImage(file);
		mav.setViewName("redirect:/member/profile");
		return mav;
	}
	
	@PostMapping
	public ModelAndView editProfile(
			@Valid @ModelAttribute("profile") ProfileForm profileForm,
			BindingResult result,
			ModelAndView mav) {

		
		
		
		if(result.hasErrors()) {
			log.info(result.getAllErrors().toString());
			mav.setViewName("/client/profile.html");
			return mav;
		}
		mav = getFormErrors(profileForm);
		if(!mav.isEmpty()) {
			mav.setViewName("client/profile");
			return mav;
		}
		userService.updateProfile(profileForm);
		mav.setViewName("redirect:/member/profile");
		return mav;
	}
	
	@GetMapping("/order")
	public ModelAndView viewProfileOrderPage(
			ModelAndView mav,
			@RequestParam(value = "page", required =  false) String page) {
		
		if(Objects.isNull(page)) {
			page = "0";
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		User user = userService.getUserById(userPrincipal.getId());
		
		
		PageResponse<Order> orders = orderService.getOrderByUserAndPage(user,Integer.parseInt(page));
		if(orders.getContent().size() == 0) {
			mav.setViewName("redirect:/profile/order");
		}
		mav.addObject("page",Integer.parseInt(page));
		mav.addObject("isFirst",orders.isFirst());
		mav.addObject("isLast",orders.isLast());
		mav.addObject("totalPage",orders.getTotalPages());
		mav.addObject("orders", orders.getContent());
		mav.setViewName("client/profile-order");
		return mav;
	}

	private ModelAndView getFormErrors(ProfileForm profileForm) {
		ModelAndView mav = new ModelAndView();
		HashMap<String, String> errors = new HashMap<>();
		if(ConstraintUltils.isContainSpecialChar(profileForm.getFirstName())) {
			errors.put("firstNameSpecial", "Tên không được phép chứa ký tự đặc biệt");
		}
		if(ConstraintUltils.isContainSpecialChar(profileForm.getLastName())) {
			errors.put("lastNameSpecial", "Họ không được phép chứa ký tự đặc biệt");
		}
		if(ConstraintUltils.isContainSpecialChar(profileForm.getProvince())) {
			errors.put("provinceSpecial", "Tỉnh không được phép chứa ký tự đặc biệt");
		}
		if(ConstraintUltils.isContainSpecialChar(profileForm.getDistrict())) {
			errors.put("districtSpecial", "Quận/Huyện không được phép chứa ký tự đặc biệt");
		}
		if(ConstraintUltils.isContainSpecialChar(profileForm.getSubDistrict())) {
			errors.put("subDistrictSpecial", "Phường/Xã không được phép chứa ký tự đặc biệt");
		}
		if(errors.size()>0) {
			mav.addObject("formErrors",errors);
		}
		return mav;
	}
	
	private Address findPrimaryAddress(User user) {

		List<Address> addresses = addressService.getAddressByUser(user);
		if (Objects.isNull(addresses)) {
			return null;
		}

		for (Address address : addresses) {
			if (Objects.nonNull(address.getIsPrimary()) && address.getIsPrimary()) {
				return address;
			}
		}
		return null;

	}

	private ModelAndView renderObjects() {
		ModelAndView mav = new ModelAndView();
		
		// Get authenticated usser
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		User user = userService.getUserById(userPrincipal.getId());
		
		List<Order> orders = orderService.getTop3OrderByUser(user);
		log.info(String.valueOf(orders.size()));
		if(orders.size()>0) {
			mav.addObject("orders", orders);
		}
		// return credential of authenticated user in database
		ProfileForm profileForm = mapToProfileForm(user);
		mav.addObject("profile",profileForm);
		mav.setViewName("client/profile");
		return mav;
	}
	
	private ProfileForm mapToProfileForm(User user) {
		
		
		// find primary address
		Address address = findPrimaryAddress(user);
		if (Objects.isNull(address)) {
			log.error("Address is null");
		}

		
		ProfileForm profileForm = ProfileForm.builder()
				.username(user.getUsername()==null?null:user.getUsername())
				.firstName(user.getFirstName()==null?null:user.getFirstName())
				.lastName(user.getLastName()==null?null:user.getLastName())
				.email(user.getEmail()==null?null:user.getEmail())
				.imageName(user.getImage()==null?null:user.getImage().getTitle())
				.thumbnailName(user.getImage()==null?null:user.getImage().getThumbnailName())
				.gender(user.getGender()==null?null:user.getGender().toString())
				.birthday(user.getBirthday()==null?null:user.getBirthday().toString())
				.phoneNumber(user.getPhoneNumber()==null?null:user.getPhoneNumber())
				.province(address==null?null:address.getProvince())
				.district(address==null?null:address.getDistrict())
				.subDistrict(address==null?null:address.getSubDistrict())
				.street(address==null?null:address.getStreet())
				.fulllAddress(address==null?null:address.fetchFullAddress())
				.build();
		
		return profileForm;
				

	}
}
