package com.metis.book.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CheckoutForm {

	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private Integer gender;
	private String phoneNumber;
	private LocalDate birthday;
	private String fullAddress;
	private String street;
	private String district;
	private String subDistrict;
	private String province;
}
