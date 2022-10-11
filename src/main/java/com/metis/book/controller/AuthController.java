package com.metis.book.controller;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

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

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {
	
	@GetMapping("/login")
	public ModelAndView viewLoginPage(
			@RequestParam(name = "error") Optional<String> error) {

		ModelAndView mav = new ModelAndView();

		// check if user already authenticated
		String viewName = redirectUser();
		if(viewName!="") {
			mav.setViewName(viewName);
			return mav;
		}
		
		
		if(error.isPresent()) {
			log.info(error.get());
			mav.addObject("errorMessage", "Email hoặc mật khẩu không chính xác. Vui lòng nhấn \"Quên mật khẩu?\" để đặt lại mật khẩu mới.");
		}
		mav.setViewName("client/login.html");
		return mav;
	}
	
	@GetMapping("/register")
	public ModelAndView viewRegisterPage() {
		
		ModelAndView mav = new ModelAndView();

		// check if user already authenticated
		String viewName = redirectUser();
		if(viewName!="") {
			mav.setViewName(viewName);
			return mav;
		}
		
		RegisterForm registerRequest = new RegisterForm();
		mav.addObject("registerRequest", registerRequest);
		mav.setViewName("client/register.html");
		return mav;
	}
	
	@PostMapping("/register-process")
	public ModelAndView register(
			@Valid @ModelAttribute("registerRequest") RegisterForm registerRequest,
			BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			log.error(result.getAllErrors().toString());
			mav.setViewName("client/register");
			return mav;
		}
		mav.setViewName("redirect:/auth/login");
		return mav;
		
	}
	
	
	private String redirectUser() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || AnonymousAuthenticationToken.class.
	      isAssignableFrom(authentication.getClass())) {
	        return "";
	    }
	    Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
	    if (roles.contains("ROLE_ADMIN")) {
            return "redirect:/admin/"; // admin page
        }else if (roles.contains("ROLE_USER")) {
        	return "redirect:/"; //home page for user
        }
	    return "";
	}

	
}
