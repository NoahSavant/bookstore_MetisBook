package com.metis.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.OK)
public class OAuth2AuthenticationProcessingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OAuth2AuthenticationProcessingException(String message) {
		super(message);
	}

	
}
