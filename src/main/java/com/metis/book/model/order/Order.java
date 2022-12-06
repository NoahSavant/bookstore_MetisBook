package com.metis.book.model.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.metis.book.model.audit.UserDateAudit;
import com.metis.book.model.user.User;
import com.metis.book.utils.AppConstant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends UserDateAudit {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "order_date", nullable = false)
	private Date orderDate;
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@Column(name = "deliver_method")
	private String deliverMethod;
	
	@Column(name = "deliver_cost")
	private String deliverCost;
	
	@Column(name = "cost_vat")
	private String costVAT;
	
	@Column(name = "discount")
	private String discount;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
	private List<OrderItem> orderItems;	
	
	@ManyToOne
	@JoinColumn(name = "order_track_id", referencedColumnName = "id", nullable = false)
	private OrderTrack orderTrack;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	public Long getTotalProductPrice() {
		long total = 0l;
		for (OrderItem orderItem : orderItems) {
			total += orderItem.getTotalPrice();
		}
		return total;
	}
	
	public Long getTotalPrice() {
		Long total = getTotalProductPrice();
		if(deliverMethod.equals("Tiêu chuẩn")) {
			total = total + AppConstant.STANDARD;
		}else if(deliverMethod.equals("Nhanh")) {
			total = total + AppConstant.FAST;
		}else if(deliverMethod.equals("Rất nhanh")) {
			total = total + AppConstant.VERY_FAST;
		}
		return total;
	}
	
	public List<OrderItem> getOrderItems() {
		return orderItems == null ? null : new ArrayList<OrderItem>(this.orderItems);
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		if(orderItems == null) {
			this.orderItems = null;
		}else {
			this.orderItems = orderItems;
		}
		
	}
	
	public int getNumProduct() {
		int total = 0;
		for (OrderItem orderItem : orderItems) {
			total += orderItem.getQuantity();
		}
		return total;
	}
}
