package com.metis.book.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class RegisterForm {

	@NotEmpty(message = "Tên đăng nhập không thể trống!")
	@Size(min = 5, max = 30, message = "Độ dài tên đăng nhập phải từ [5-30] ký tự")
	private String username;
	
	@NotEmpty(message = "Mật khẩu không thể trống")
	@Size(min = 7, max = 30, message = "Độ dài mật khẩu phải từ [5-30] ký tự")
	private String password;
	
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
	private String phoneNumber;
	
	private String birthday;

	public RegisterForm() {
		this.gender = "1";
	}
	
	
}
