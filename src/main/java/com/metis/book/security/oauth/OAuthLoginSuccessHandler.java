package com.metis.book.security.oauth;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class OAuthLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	private RequestCache requestCache;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// check if user have previous request
		SavedRequest savedRequest =  requestCache.getRequest(request, response);
		if(!Objects.isNull(savedRequest) && !savedRequest.getRedirectUrl().contains("/admin")) {
			response.sendRedirect(savedRequest.getRedirectUrl());
			return;
		}
		
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin/"); // admin page
        }else if (roles.contains("ROLE_USER")) {
        	
        	response.sendRedirect("/"); //home page for user
        }
		
	}



}
