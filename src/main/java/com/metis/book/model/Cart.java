package com.metis.book.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.metis.book.model.user.User;

import lombok.Data;

@Entity
@Table(name = "Carts")
@Data
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@OneToMany(mappedBy = "cart")
	private List<CartItem> cartItems;
	
	@OneToOne(mappedBy = "cart")
	private User user;
}
