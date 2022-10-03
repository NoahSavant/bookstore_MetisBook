package com.metis.book.model.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.metis.book.model.audit.UserDateAudit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profiles")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Profile extends UserDateAudit{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "gender")
	private Integer gender;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "birthday")
	private LocalDate birthday;
	
	@OneToOne(mappedBy = "profile")
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private List<Address> addresses;

	public List<Address> getAddresses() {
		return addresses == null ? null : new ArrayList<Address>(this.addresses);
	}

	public void setAddresses(List<Address> addresses) {
		if(addresses == null) {
			this.addresses = null;
		}
		this.addresses = addresses;
	}
	
	
}
