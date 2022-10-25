package com.metis.book.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProfileForm {

	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private Integer gender;
	private String phoneNumber;
	private LocalDate birthday;


}
