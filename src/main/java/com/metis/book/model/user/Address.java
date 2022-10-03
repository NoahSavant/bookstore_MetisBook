package com.metis.book.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.metis.book.model.audit.UserDateAudit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
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
	private String provinces;
	
	@Column(name = "full_address")
	private String fullAddress;
	
	@Column(name = "is_primary")
	private Boolean isPrimary;
}
