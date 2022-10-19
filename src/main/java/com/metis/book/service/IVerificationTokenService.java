package com.metis.book.service;

import javax.servlet.http.HttpServletRequest;

import com.metis.book.model.VerificationToken;
import com.metis.book.model.user.User;

public interface IVerificationTokenService {

	VerificationToken getVerificationToken(String token);

	String sendVerificationToken(HttpServletRequest request, VerificationToken newToken, User user);
	
	VerificationToken getTokenByUser(User savedUser);


}
