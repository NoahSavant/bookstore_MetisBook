package com.metis.book.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	
	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if(exception.getMessage().equals("User is disabled")) {
			
			String email = request.getParameter("email");
			request.getSession().setAttribute("email", email);
			response.sendRedirect("/auth/login?disabled=true");
		}else {
			request.getSession().setAttribute("errorMessage", "Email hoặc mật khẩu không chính xác. Vui lòng nhấn \"Quên mật khẩu?\" để đặt lại mật khẩu mới.");
			response.sendRedirect("/auth/login?error=true");
		}
		
		
	}

}
