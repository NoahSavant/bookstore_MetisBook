package com.metis.book.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.metis.book.model.audit.UserDateAudit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "addresses")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Address extends UserDateAudit{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "street")
	private String street;
	
	@Column(name ="district")
	private String district;
	
	@Column(name = "sub_district")
	private String subDistrict;
	
	@Column(name = "province")
	private String province;
	
	@Column(name = "full_address")
	private String fullAddress;
	
	@Column(name = "is_primary")
	private Boolean isPrimary;
	
	@Column(name = "recieve_phone")
	private String recievePhoneNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	public String fetchFullAddress() {
	
		String fullAddress = this.fullAddress == null || this.fullAddress.equals("")? "": this.fullAddress + ", ";
		if(this.street!="" && this.street!=null) {
			fullAddress = fullAddress + this.street+", ";
		}
		if(this.subDistrict!="" && this.subDistrict!=null) {
			fullAddress = fullAddress + this.subDistrict+", ";
		}
		if(this.district!="" && this.district !=null) {
			fullAddress = fullAddress + this.district+", ";
		}
		if(this.province!="" && this.province !=null) {
			fullAddress = fullAddress + this.province;
		}
		String lastChar = fullAddress.substring(fullAddress.length() - 1);
		
		// check if last char is ',' then delete it
		if(lastChar.equals(",")) {
			fullAddress = fullAddress.substring(0, fullAddress.length() - 1);  
		}
		return fullAddress;
	}

	
	
}
