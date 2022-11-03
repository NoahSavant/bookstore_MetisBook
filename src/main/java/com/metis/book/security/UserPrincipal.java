package com.metis.book.security;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.metis.book.model.Image;
import com.metis.book.model.user.Role;
import com.metis.book.model.user.User;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class UserPrincipal implements UserDetails, OAuth2User {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private Integer gender;
	private String phoneNumber;
	private String cartItemNum;
	private LocalDate birthday;
	private Boolean enabled;
	private Image image;
	private Map<String, Object> attributes;
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
		for (Role r : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getName()));
		}
		int itemNum = 0;
		if(Objects.nonNull(user.getCart().getCartItems())) {
			itemNum = user.getCart().getCartItems().size();
		}
		
		return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(),
				user.getFirstName(), user.getLastName(), user.getGender(), user.getPhoneNumber(), user.getBirthday(),
				user.getEnabled(), user.getImage(), authorities, String.valueOf(itemNum)); // roles

	}

	// Create user for oauth2
	public static UserPrincipal create(User user, Map<String, Object> attributes) {
		UserPrincipal userPrincipal = UserPrincipal.create(user);
		userPrincipal.setAttributes(attributes);
		return userPrincipal;
	}

	public UserPrincipal() {
		super();

	}

	

	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	@Override
	public String getName() {
		return String.valueOf(this.id);
	}

	public UserPrincipal(Long id, String username, String password, String email, String firstName, String lastName,
			Integer gender, String phoneNumber, LocalDate birthday, Boolean enabled, Image image, List<GrantedAuthority> authorities,String cartItemNum) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.enabled = enabled;
		this.image = image;
		this.authorities = authorities;
		this.cartItemNum = cartItemNum;
	}

}
