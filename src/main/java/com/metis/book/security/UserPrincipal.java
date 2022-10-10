package com.metis.book.security;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.metis.book.model.user.Role;
import com.metis.book.model.user.User;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data	
@Slf4j
public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Integer gender;
	private String phoneNumber;
	private LocalDate birthday;
	private Boolean enabled;
	
	private List<GrantedAuthority> authorities; // roles
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities == null ? null : new ArrayList<>(this.authorities);
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public static UserPrincipal create(User user) {

		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Role r : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+r.getName()));
		}
		
		return new UserPrincipal(
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				user.getFirstName(),
				user.getLastName(),
				user.getGender(),
				user.getPhoneNumber(),
				user.getBirthday(),
				user.getEnabled(),
				authorities); // roles
		
	}

	public UserPrincipal() {
		super();

	}

	public UserPrincipal(Long id, String username, String password, String firstName, String lastName, Integer gender,
			String phoneNumber, LocalDate birthday, Boolean enabled, List<GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.enabled = enabled;
		this.authorities = authorities;
	}


	
}
