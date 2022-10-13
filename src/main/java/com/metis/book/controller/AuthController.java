package com.metis.book.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.dto.RegisterForm;
import com.metis.book.event.OnRegistrationCompleteEvent;
import com.metis.book.model.VerificationToken;
import com.metis.book.model.user.User;
import com.metis.book.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {

	@Autowired
	IUserService userService;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@GetMapping("/login")
	public ModelAndView viewLoginPage(@RequestParam(name = "error") Optional<String> error) {

		ModelAndView mav = new ModelAndView();

		// check if user already authenticated
		String viewName = redirectUser();
		if (viewName != "") {
			mav.setViewName(viewName);
			return mav;
		}

		if (error.isPresent()) {
			log.info(error.get());
			mav.addObject("errorMessage",
					"Email hoặc mật khẩu không chính xác. Vui lòng nhấn \"Quên mật khẩu?\" để đặt lại mật khẩu mới.");
		}
		mav.setViewName("client/login.html");
		return mav;
	}

	@GetMapping("/register")
	public ModelAndView viewRegisterPage() {

		ModelAndView mav = new ModelAndView();

		// check if user already authenticated
		String viewName = redirectUser();
		if (viewName != "") {
			mav.setViewName(viewName);
			return mav;
		}

		RegisterForm registerRequest = new RegisterForm();
		mav.addObject("registerRequest", registerRequest);
		mav.setViewName("client/register.html");
		return mav;
	}

	@PostMapping("/register-process")
	public ModelAndView register(@Valid @ModelAttribute("registerRequest") RegisterForm registerRequest,
			HttpServletRequest request, BindingResult result) {

		ModelAndView mav = new ModelAndView();

		// Check constraint
		mav = checkViolation(result, registerRequest);
		if (!mav.isEmpty()) {
			return mav;
		}

		// create new User
		User savedUser = userService.createNewUser(registerRequest);

		// Publish even send email with verification token
		publishEvent(savedUser, request);

		mav.setViewName("redirect:/auth/login");
		return mav;

	}

	@PostMapping(name = "/register-confirm")
	public ModelAndView registerConfirm(@RequestParam(name = "token") String token, HttpServletRequest request) {
		Locale locale = request.getLocale();

		VerificationToken verificationToken = service.getVerificationToken(token);
		if (verificationToken == null) {
			String message = messages.getMessage("auth.message.invalidToken", null, locale);
			model.addAttribute("message", message);
			return "redirect:/badUser.html?lang=" + locale.getLanguage();
		}
		return null;
	}

	private void publishEvent(User user, HttpServletRequest request) {
		final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
	}

	private ModelAndView checkViolation(BindingResult result, RegisterForm registerRequest) {

		ModelAndView mav = new ModelAndView();
		// Check constraint on info
		if (result.hasErrors()) {
			mav.setViewName("client/register");
			return mav;
		}

		Map<String, String> authenErrors = getAuthenError(registerRequest);

		// Check constraint on data - duplicate, not match,...
		if (authenErrors.size() > 0) {
			mav.addObject("authenErrors", authenErrors);
			mav.setViewName("client/register");
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
		if (isContainSpecialChar(registerRequest.getUsername())) {
			errors.put("usernameSpecial", "Tên đăng nhập không được phép chứa ký tự đặc biệt");
		}
		if (isContainSpecialChar(registerRequest.getFirstName())) {
			errors.put("firstNameSpecial", "Tên không được phép chứa ký tự đặc biệt");
		}
		if (isContainSpecialChar(registerRequest.getLastName())) {
			errors.put("lastNameSpecial", "Họ không được phép chứa ký tự đặc biệt");
		}
		if (isExistByEmail(registerRequest.getEmail())) {
			errors.put("existByEmail", "Email đã tồn tại");
		}

		log.error(errors.toString());
		return errors;
	}

	private Boolean isContainSpecialChar(String username) {

		Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]"); // fill in any chars that you consider special

		if (regex.matcher(username).find()) {
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

	private Boolean isExistByEmail(String email) {
		if (userService.existsByEmail(email)) {
			return true;
		}
		return false;
	}

	private String redirectUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
			return "";
		}
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if (roles.contains("ROLE_ADMIN")) {
			return "redirect:/admin/"; // admin page
		} else if (roles.contains("ROLE_USER")) {
			return "redirect:/"; // home page for user
		}
		return "";
	}
}
