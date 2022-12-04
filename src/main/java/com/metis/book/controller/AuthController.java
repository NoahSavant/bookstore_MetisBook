package com.metis.book.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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
import com.metis.book.dto.ResetPasswordForm;
import com.metis.book.event.OnRegistrationCompleteEvent;
import com.metis.book.model.PasswordResetToken;
import com.metis.book.model.VerificationToken;
import com.metis.book.model.user.User;
import com.metis.book.service.IPasswordResetTokenService;
import com.metis.book.service.IUserService;
import com.metis.book.service.IVerificationTokenService;
import com.metis.book.utils.ConstraintUltils;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {

	@Autowired
	IUserService userService;

	@Autowired
	IVerificationTokenService verificationTokenService;

	@Autowired
	IPasswordResetTokenService passwordTokenService;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@GetMapping("/login")
	public ModelAndView viewLoginPage(@RequestParam(name = "error") Optional<String> error,
			@RequestParam(name = "disabled") Optional<String> disabled, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		// check if user already authenticated
		String viewName = redirectUser();
		if (viewName != "") {
			mav.setViewName(viewName);
			return mav;
		}

		// check if user account not yet verify then verify
		if (disabled.isPresent()) {
			return viewVerifyPage(request, mav);
		}
		// check if there are any error on authentication
		else if (error.isPresent()) {
			String errorMessage = request.getSession().getAttribute("errorMessage").toString();
			mav.addObject("errorMessage", errorMessage);
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
			BindingResult result, HttpServletRequest request) {
		log.error("In register-process");
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

		// Get token
		mav.addObject("email", savedUser.getEmail());
		mav.addObject("userId", savedUser.getId());
		mav.addObject("message", "Xin vui lòng xác thực tài khoản của bạn.");
		mav.setViewName("client/verify.html");
		return mav;
	}

	@GetMapping("/register-confirm")
	public ModelAndView registerConfirm(@RequestParam(name = "token") String token, HttpServletRequest request,
			ModelAndView mav) {

		log.info("In registerConfirm");
		VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
		if (Objects.isNull(verificationToken)) {
			String message = "Đường dẫn xác thực không đúng";
			mav.addObject("message", message);
			mav.setViewName("client/exception/bad-user.html");
			return mav;
		}

		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			String message = "Đường dẫn xác thực đã hết hạn";
			mav.addObject("isAccountVerify", "true");
			mav.addObject("message", message);
			mav.setViewName("client/exception/bad-user.html");
			return mav;
		}

		user.setEnabled(true);
		userService.updateUser(user);
		mav.setViewName("redirect:/auth/login");
		return mav;
	}

	@GetMapping("/resend-register-token")
	public ModelAndView resendRegistrationToken(final HttpServletRequest request,
			@RequestParam(name = "token", required = false) String existingToken,
			@RequestParam(name = "userId", required = false) Long userId, ModelAndView mav) {
		VerificationToken newToken;

		if (userId != null) {
			log.info(userId.toString());
			newToken = userService.generateVerifyTokenById(userId);
		} else if (existingToken != null) {
			newToken = userService.generateNewVerificationToken(existingToken);
		} else {
			String message = "Đường dẫn xác thực không đúng";
			mav.addObject("message", message);
			mav.setViewName("client/exception/bad-user.html");
			return mav;
		}

		User user = userService.getUserByVerificationToken(newToken.getToken());
		verificationTokenService.sendVerificationToken(request, newToken, user);

		mav.addObject("email", user.getEmail());
		mav.addObject("userId", user.getId());
		mav.addObject("message", "Đường dẫn xác thực đã được làm mới");
		mav.setViewName("client/verify.html");
		return mav;
	}

	@GetMapping("/forgot-password")
	public ModelAndView viewForgotPasswordPage(ModelAndView mav) {
		mav.setViewName("client/forgot-password.html");
		return mav;
	}

	@PostMapping("/forgot-password")
	public ModelAndView resetPassword(final HttpServletRequest request, @Valid @ModelAttribute("email") String email,
			BindingResult result, ModelAndView mav) {
		log.info(email);
		if (result.hasErrors()) {
			mav.addObject("errorMessage", "Email không hợp lệ");
			mav.setViewName("client/forgot-password.html");
			return mav;
		}

		
		User user = userService.findByEmail(email);
		if (Objects.isNull(user)) {
			mav.addObject("errorMessage", "Không tìm thấy tài khoản với địa chỉ email này");
			mav.setViewName("client/forgot-password.html");
			return mav;
		}

		PasswordResetToken existingtoken = passwordTokenService.getPasswordTokenByUser(user);
		PasswordResetToken newToken;
		if (Objects.isNull(existingtoken)) {
			newToken = userService.generatePasswordTokenByUser(user);
		} else {
			log.info(existingtoken.getToken());
			newToken = userService.generateNewPasswordToken(existingtoken.getToken());
		}
		passwordTokenService.sendResetPasswordToken(request, newToken, user);
		mav.addObject("isSuccess", "Chúng tôi đã gửi cho bạn đường link đặt lại mật khẩu, xin hãy kiểm tra hộp thư");
		mav.setViewName("client/forgot-password.html");
		return mav;
	}

	@GetMapping("/update-password")
	public ModelAndView viewUpdatePasswordPage(
			@RequestParam(name = "token") String token,
			ModelAndView mav) {
		
		User user = userService.getUserByPasswordToken(token);
		PasswordResetToken passwordToken = passwordTokenService.getPasswordTokenByUser(user);
		// Check violation
		mav = checkPassWordToken(user, token, passwordToken);
		if (!mav.isEmpty()) {
			log.info("aaaaaaaaaaaaa");
			return mav; // if have violation on token
		}

		mav.addObject("token", token);
		mav.setViewName("client/update-password.html");
		return mav;
	}

	@PostMapping("/update-password")
	public ModelAndView updatePassword(@RequestParam(name = "token") String token,
			@ModelAttribute("resetPassword") ResetPasswordForm passwordForm, 
			BindingResult result, 
			ModelAndView mav) {

		if (result.hasErrors()) {
			mav.addObject("errorMessage", "Mật khẩu không hợp lệ");
			mav.setViewName("client/update-password.html");
			return mav;
		}
		if (!passwordForm.getPassword().equals(passwordForm.getConfirmPassword())) {
			mav.addObject("errorMessage", "Mật khẩu nhập lại không khớp");
			mav.setViewName("client/update-password.html");
			return mav;
		}
		User user = userService.getUserByPasswordToken(token);
		PasswordResetToken passwordToken = passwordTokenService.getPasswordTokenByUser(user);
		
		// Check violation
		mav = checkPassWordToken(user, token, passwordToken);
		if (!mav.isEmpty()) {
			return mav; // if have violation on token
		}

		String password = passwordForm.getPassword();
		userService.updatePassword(passwordToken.getToken(), password);
		mav.setViewName("redirect:/auth/login");
		return mav;
	}

	private ModelAndView checkPassWordToken(User user, String token, PasswordResetToken passwordToken) {
		ModelAndView mav = new ModelAndView();
		if (Objects.isNull(user)) {
			String message = "Đường dẫn thay đổi mật khẩu không đúng";
			mav.addObject("message", message);
			mav.setViewName("client/exception/bad-user.html");
			return mav;
		}

		Calendar cal = Calendar.getInstance();
		if ((passwordToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			String message = "Đường dẫn thay đổi mật khẩu đã hết hạn";
			mav.addObject("message", message);
			mav.setViewName("client/exception/bad-user.html");
			return mav;
		}
		return mav;
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

	private ModelAndView viewVerifyPage(HttpServletRequest request, ModelAndView mav) {
			// get session from LoginFailureHandler
			String email = request.getSession().getAttribute("email").toString();
			User user = userService.findByEmail(email);
			mav.addObject("userId", user.getId());
			mav.addObject("email", user.getEmail());
			mav.addObject("message", "Xin vui lòng xác thực tài khoản của bạn.");
			mav.setViewName("client/verify.html");
			return mav;
	}
}
