package com.metis.book.service;

import com.metis.book.dto.RegisterForm;

public interface UserService {

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	void createNewUser(RegisterForm registerRequest);

}
