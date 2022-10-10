package com.metis.book.model.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.metis.book.model.Cart;
import com.metis.book.model.audit.UserDateAudit;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User extends UserDateAudit  {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
		
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
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)	
	private List<Address> addresses;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	private Cart cart;
	
	public List<Address> getAddresses() {
		return addresses == null ? null : new ArrayList<Address>(this.addresses);
	}
	
	public void setAddresses(List<Address> addresses) {
		if(addresses == null) {
			this.addresses = null;
		}
		this.addresses = addresses;
	}
	
	public List<Role> getRoles() {
		return roles == null ? null : new ArrayList<Role>(this.roles);
	}

	public void setRoles(List<Role> roles) {
		if(roles == null) {
			this.roles = null;
		}
		this.roles = roles;
	}

}
