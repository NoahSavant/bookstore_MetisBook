package com.metis.book.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import com.metis.book.model.user.Role;
import com.metis.book.model.user.RoleName;
import com.metis.book.model.user.User;

import lombok.Data;

@Data
public class UserForm {
	
	private String id;
	private String username;
	private String password;
	private String confirmPassword;
	private String email;
	private String firstName;
	private String lastName;
	private String gender;
	private String phoneNumber;
	private String birthday;
	private List<String> addresses = new ArrayList<>();
	private String primaryAddress;
	private String role;
	private String authProvider;
	private String enabled;
	private String image;
	private String createBy;
	private String lastUpdateBy;
	private String createDate;
	private String lastUpdateDate;
	private MultipartFile file;
	
	public void convert(User user) {
		this.id = user.getId().toString();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		
		// Check gender
		if(user.getGender() == 0) {
			this.gender = "Khác";
		}else if(user.getGender() == 1) {
			this.gender = "Nam";
		}else {
			this.gender = "Nữ";
		}
		this.phoneNumber = user.getPhoneNumber();
		if(Objects.nonNull(user.getBirthday())) {
			this.birthday = user.getBirthday().toString();
		}else {
			this.birthday = "";
		}
		
		
		// Check auth provider
		if(Objects.nonNull(user.getAuthProvider())) {
			this.authProvider = user.getAuthProvider().toString() ;
		}else {
			this.authProvider = "";
		}
		
		// check enabled
		if(user.getEnabled() == true) {
			this.enabled = "Hoạt động";
		}else {
			this.enabled = "Chặn";
		}
		
	
		this.role = "Khách hàng";
		for (Role role : user.getRoles()) {
			if(role.getName().equals(RoleName.ADMIN)) {
				this.role = "Quản lý";
				return;
			}else if(role.getName().equals(RoleName.STAFF)) {
				this.role = "Nhân viên";
			}
		}
		
		
	}
}
