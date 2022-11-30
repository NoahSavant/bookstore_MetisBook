package com.metis.book.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.metis.book.validation.Phone;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterForm {

	@NotEmpty(message = "Tên đăng nhập không thể trống!")
	@Size(min = 2, max = 30, message = "Độ dài tên đăng nhập phải từ [2-30] ký tự")
	private String username;
	
	@NotEmpty(message = "Mật khẩu không thể trống")
	@Size(min = 7, max = 30, message = "Độ dài mật khẩu phải từ [7-30] ký tự")
	private String password;

	@NotEmpty(message = "Mật khẩu nhập lại không thể trống")
	private String confirmPassword;
	
	@NotEmpty(message = "Email không thể trống")
	@Size(min = 4, max = 30, message = "Độ dài email phải từ [4-30] ký tự")
	@Email(message = "Email không hợp lệ")
	private String email;
	
	@NotEmpty(message = "Tên không thể trống")
	@Size(min = 2, max = 30, message = "Độ dài tên phải từ [2-30] ký tự")
	private String firstName;
	
	@NotEmpty(message = "Họ không thể trống")
	@Size(min = 2, max = 30, message = "Độ dài họ phải từ [2-30] ký tự")
	private String lastName;
	
	@NotEmpty(message = "Giới tính không thể trống")
	private String gender;
	
	@NotEmpty(message = "Số điện thoại không thể trống")
	@Size(min = 6, max =12, message = "Độ dài số điện thoại phải từ [6-12] ký tự")
	@Phone(message = "Số điện thoại không hợp lệ")
	private String phoneNumber;
	

	private String street;
	private String district;
	private String subDistrict;
	private String province;
	private String fullAddress;
	private String birthday;
	
	private MultipartFile file;	

	public RegisterForm() {
		this.gender = "1";
	}
	
	
}
