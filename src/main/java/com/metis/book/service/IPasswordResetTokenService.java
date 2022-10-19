package com.metis.book.service;

import javax.servlet.http.HttpServletRequest;

import com.metis.book.model.PasswordResetToken;
import com.metis.book.model.user.User;

public interface IPasswordResetTokenService {

	PasswordResetToken findByUser(User user);
	String sendResetPasswordToken(HttpServletRequest request, PasswordResetToken newToken, User user);
	PasswordResetToken getPasswordTokenByUser(User user);
}
