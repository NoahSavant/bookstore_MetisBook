package com.metis.book.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.metis.book.model.audit.UserDateAudit;
import com.metis.book.model.order.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Book extends UserDateAudit {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private Long price;
	
	@Column(name = "publication_date")
	private Date date;
	
	@Column(name = "publisher_name")
	private String publisherName;
	
	@Column(name = "available")
	private Boolean available;
	
	@OneToMany(mappedBy = "book")
	private List<Language> languages;
	
	@OneToOne
	@JoinColumn(name = "stock_id", referencedColumnName = "id")
	private Stock stock;
	
	@ManyToMany
	@JoinTable(
			name = "book_author",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> authors;

	
	@OneToMany(mappedBy = "book")
	private List<CartItem> carts;
	
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;
	
	@OneToMany(mappedBy = "book")
	private List<OrderItem> orderItems;

	
	public List<OrderItem> getOrderItems() {
		return orderItems == null ? null : new ArrayList<OrderItem>(this.orderItems);
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		if(orderItems == null) {
			this.orderItems = null;
		}
		this.orderItems = orderItems;
	}

	public List<CartItem> getCarts() {
		return carts == null ? null : new ArrayList<CartItem>(this.carts);
	}

	public void setCarts(List<CartItem> carts) {
		if(carts == null) {
			this.carts = null;
		}
		this.carts = carts;
	}

	public List<Language> getLanguages() {
		return languages == null ? null : new ArrayList<Language>(this.languages);
	}

	public void setLanguages(List<Language> languages) {
		if(languages == null) {
			this.languages = null;
		}
		this.languages = languages;
	}

	public List<Author> getAuthors() {
		return authors == null ? null : new ArrayList<Author>(this.authors);
	}

	public void setAuthors(List<Author> authors) {
		if(authors == null) {
			this.authors = null;
		}
		this.authors = authors;
	}
	
	
	
	
	

}
