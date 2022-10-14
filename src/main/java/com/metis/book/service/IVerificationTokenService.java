package com.metis.book.service;

import com.metis.book.model.VerificationToken;

public interface IVerificationTokenService {

	VerificationToken getVerificationToken(String token);

}
