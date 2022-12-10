package com.metis.book.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class AimForm {
	private String totalRevenue;
	private String numOrder;
	private String numCustomer;
	private String avgProReview;
	private String numProSold;
	private String areaYear;
	private String pie1Year;
	private String pie2Year;
	private String cardYear;
	
	private int getCurrentYear() {
		Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        return year;
	}
	
	public void setData(String data) {
		String[] list = data.split(" ");
		totalRevenue = list[0];
		numOrder = list[1];
		numCustomer = list[2];
		avgProReview = list[3];
		numProSold = list[4];
	}
	
	public void setYear(int year) {
		areaYear = String.valueOf(year);
		pie1Year = String.valueOf(year);
		pie2Year = String.valueOf(year);
		cardYear = String.valueOf(year);
	}
	
	public String getData() {
		String data = "";
		data += totalRevenue + " " + numOrder + " " + numCustomer + " " + 
				avgProReview + " " + numProSold;
		return data;
	}
	
	public int getCustomAreaYear() {
		int result = getCurrentYear();
		try {
			result = Integer.parseInt(areaYear);
			return result;
		}
		catch (Exception e) {
			return result;
		}
	}
	
	public int getCustomPie1Year() {
		int result = getCurrentYear();
		try {
			result = Integer.parseInt(pie1Year);
			return result;
		}
		catch (Exception e) {
			return result;
		}
	}
	public int getCustomPie2Year() {
		int result = getCurrentYear();
		try {
			result = Integer.parseInt(pie2Year);
			return result;
		}
		catch (Exception e) {
			return result;
		}
	}
	public int getCustomCardYear() {
		int result = getCurrentYear();
		try {
			result = Integer.parseInt(cardYear);
			return result;
		}
		catch (Exception e) {
			return result;
		}
	}
}
