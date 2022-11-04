package com.metis.book.dto;

import java.util.List;

import lombok.Data;

@Data
public class CheckoutForm {

	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String fullAddress;
	private String newAddress;
	private String street;
	private String district;
	private String subDistrict;
	private String province;
	private String paymentMethod;
	private String deliverMethod;
	private List<String> checkoutItems;
	private Boolean isPrimary;
}
