package com.metis.book.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.model.VerificationToken;
import com.metis.book.repository.VerificationTokenRepository;
import com.metis.book.service.IVerificationTokenService;

@Service
public class VerificationTokenServiceImpl implements IVerificationTokenService {

	@Autowired
	VerificationTokenRepository tokenRepository;
	
	@Override
	public VerificationToken getVerificationToken(String token) {
		return tokenRepository.findByToken(token);
	}

}
