package com.metis.book.service;

import com.metis.book.dto.RegisterForm;
import com.metis.book.model.user.User;

public interface UserService {

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	User createNewUser(RegisterForm registerRequest);

	void createVerificationTokenForUser(User user, String token);

}
