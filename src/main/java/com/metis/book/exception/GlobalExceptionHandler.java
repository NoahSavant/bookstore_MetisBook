package com.metis.book.exception;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public void usernameNotFoundException(UsernameNotFoundException e) throws ServletException, IOException {

	 	log.info("Username or password not correct");

	}
	
}
