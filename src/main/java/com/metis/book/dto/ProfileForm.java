package com.metis.book.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.metis.book.validation.Phone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileForm {

	@Size(min = 2, max = 30, message = "Độ dài tên đăng nhập phải từ [2-30] ký tự")
	private String username;
	
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
	
	private String birthday;
	
	@NotEmpty(message = "Số nhà không thể trống")
	private String street;
	
	@NotEmpty(message = "Quận/Huyện không thể trống")
	@Size(min = 2, max =20, message = "Độ dài số quận/huyện phải từ [2-20] ký tự")
	private String district;
	
	@NotEmpty(message = "Phường/Xã không thể trống")
	@Size(min = 2, max =20, message = "Độ dài phường/xã phải từ [2-20] ký tự")
	private String subDistrict;
	
	@NotEmpty(message = "Tỉnh không thể trống")
	@Size(min = 2, max =20, message = "Độ dài tỉnh phải từ [2-20] ký tự")
	private String province;
	
	private String fulllAddress;
	private String imageName;
	private String thumbnailName;

}
