package com.metis.book.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.metis.book.model.user.User;
import com.metis.book.utils.AppConstant;

import lombok.Data;

@Entity
@Table(name = "Carts")
@Data
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@OneToMany(mappedBy = "cart",fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CartItem> cartItems;
	
	@OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
	private User user;

	public Long getTotalPrice(String deliverMethod) {
		Long total = 0L;
		if(this.cartItems.size()>0) {
			for (CartItem item : cartItems) {
				total = total + item.getTotalPrice();
			}
		}
		if(deliverMethod.equals("Tiêu chuẩn")) {
			total = total + AppConstant.STANDARD;
		}else if(deliverMethod.equals("Nhanh")) {
			total = total + AppConstant.FAST;
		}else if(deliverMethod.equals("Rất nhanh")) {
			total = total + AppConstant.VERY_FAST;
		}
		return total;
	}
	
	public Long getTotalPrice() {
		Long total = 0L;
		if(this.cartItems.size()>0) {
			for (CartItem item : cartItems) {
				total = total + item.getTotalPrice();
			}
		}
		return total;
	}
	
	public List<CartItem> getCartItems() {
		return cartItems==null?null:new ArrayList<>(this.cartItems);
	}

	public void setCartItems(List<CartItem> cartItems) {
		if(cartItems==null) {
			this.cartItems = null;
			return;
		}
		this.cartItems = cartItems;
	}
}