package com.metis.book.model.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.metis.book.model.Cart;
import com.metis.book.model.Feedback;
import com.metis.book.model.Image;
import com.metis.book.model.audit.UserDateAudit;
import com.metis.book.model.order.Order;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", 
uniqueConstraints = {
		@UniqueConstraint(name = "UniqueUsername",columnNames = "username"),
		@UniqueConstraint(name = "UniqueEmail", columnNames = "email")
})
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
	
	@Column(name = "username",length = 30)
	private String username;
	
	@Column(name = "password", length = 100)
	private String password;
		
	@Column(name = "email", nullable = false, length = 30)
	private String email;
	
	@Column(name = "first_name", length = 30)
	private String firstName;
	
	@Column(name = "last_name", length = 30)
	private String lastName;
	
	@Column(name = "gender")
	private Integer gender;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "birthday")
	private LocalDate birthday;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)	
	private List<Address> addresses;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Order> orders;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "auth_provider")
	private AuthProvider authProvider;
	
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;
	
	@OneToOne
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	private Cart cart;
	
	@OneToOne
	@JoinColumn(name = "image_id", referencedColumnName = "id")
	private Image image;
	
	@OneToMany (mappedBy = "user", fetch = FetchType.LAZY)
	private List<Feedback> feedbacks;

	
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

	public List<Order> getOrders() {
		return orders==null?null:new ArrayList<>(this.orders);
	}

	public void setOrders(List<Order> orders) {
		if(orders == null) {
			this.orders = null;
		}else {
			this.orders = orders;
		}
		
	}
	
	public List<Feedback> getFeedbacks() {
		return feedbacks==null?null:new ArrayList<>(this.feedbacks);
	}
	public void setFeedbacks(List<Feedback> feedbacks) {
		if(feedbacks == null) {
			this.feedbacks = null;
		}else {
			this.feedbacks = feedbacks;
		}
		
	}
	
	public long totalPurchase(int year) {
		long total = 0;
		for (Order order : this.orders) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(order.getOrderDate());
			if(calendar.get(Calendar.YEAR) == year) {
				total += order.getTotalPrice();
			}
		}
		return total;
	}
}
