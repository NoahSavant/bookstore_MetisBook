package com.metis.book.dto;

import java.util.List;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import com.metis.book.model.user.Address;
import com.metis.book.model.user.Role;
import com.metis.book.model.user.RoleName;
import com.metis.book.model.user.User;

import lombok.Data;

@Data
public class UserEditForm {
	private String id;
	private String password;
	private String confirmPassword;
	private String firstName;
	private String lastName;
	private String gender;
	private String phoneNumber;
	private String birthday;
	private String street;
	private String district;
	private String subDistrict;
	private String province;
	private String fullAddress;
	private String enabled;
	private String imageName;
	private String thumbnailName;
	private String role;
	private MultipartFile file;

	public void convert(User user, Address address) {
		this.id = user.getId().toString();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.gender = user.getGender().toString();
		this.phoneNumber = user.getPhoneNumber();
		if (Objects.nonNull(user.getBirthday())) {
			this.birthday = user.getBirthday().toString();
		} else {
			this.birthday = "";
		}

		if(user.getEnabled() == true) {
			this.enabled = "1";
		}else {
			this.enabled = "0";
		}
		
		
		this.street = address.getStreet();
		this.province = address.getProvince();
		this.district = address.getDistrict();
		this.subDistrict = address.getSubDistrict();
		
		
		this.imageName =  user.getImage()==null?null:user.getImage().getTitle();
	    this.thumbnailName = user.getImage()==null?null:user.getImage().getThumbnailName();
	    List<Role> roles =  user.getRoles();
	    this.role = "2"; // user
	    for (Role role : roles) {
			if(role.getName().equals(RoleName.ADMIN)) {
				this.role = "1";	
				break;
			}
			if(role.getName().equals(RoleName.STAFF)) {
				this.role = "3";
			}	
		}
	    
	    this.fullAddress = address.getFullAddress();
	}
}
