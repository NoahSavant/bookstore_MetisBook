package com.metis.book.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import com.metis.book.utils.Service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class Book extends UserDateAudit {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title",nullable = false, length = 40)
	private String title;
	
	@Column(name = "description", length = 200)
	private String description;
	
	@Column(name = "price")
	private Long price;
	
	@Column(name = "publication_date")
	private Date publicationDate;
	
	@Column(name = "publisher_name", nullable = false)
	private String publisherName;
	
	@Column(name = "available", nullable = false)
	private Boolean available;
	
	@ManyToOne
	@JoinColumn(name = "language_id", referencedColumnName = "id",nullable = false)
	private Language language;
	
	@OneToOne
	@JoinColumn(name = "inventory_id", referencedColumnName = "id", nullable = false)
	private Inventory inventory;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "book_author",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> authors;
	
	@OneToOne
	@JoinColumn(name = "image_id", referencedColumnName = "id")
	private Image image;
	
	@OneToMany(mappedBy = "book")
	private List<Feedback> feedbacks;
	
	@OneToMany(mappedBy = "book")
	private List<CartItem> carts;
	
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
	private Category category;
	
	@OneToMany(mappedBy = "book")
	private List<OrderItem> orderItems;
	
	public List<OrderItem> getOrderItems() {
		return orderItems == null ? null : new ArrayList<OrderItem>(this.orderItems);
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		if(orderItems == null) {
			this.orderItems = null;
			return;
		}
		this.orderItems = orderItems;
	}

	public List<CartItem> getCarts() {
		return carts == null ? null : new ArrayList<CartItem>(this.carts);
	}

	public void setCarts(List<CartItem> carts) {
		if(carts == null) {
			this.carts = null;
			return;
		}
		this.carts = carts;
	}
	
	public List<Author> getAuthors() {
		return authors == null ? null : new ArrayList<Author>(this.authors);
	}

	public void setAuthors(List<Author> authors) {
		if(authors == null) {
			this.authors = null;
			return;
		}
		this.authors = authors;
	}
	
	public List<Feedback> getFeedbacks() {
		return feedbacks == null ? null : new ArrayList<Feedback>(this.feedbacks);
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		if(feedbacks == null) {
			this.feedbacks = null;
			return;
		}
		this.feedbacks = feedbacks;
	}
	
	public float getAvgRate() {
		float totalRate = 0;
		int totalFB = 0;
		for (Feedback fb : feedbacks) {
			int rate = fb.getRating();
			if(rate > 0) {
				totalRate += fb.getRating();
				totalFB += 1;
			} 
		}
		if(totalFB == 0) return 0;
		return totalRate/totalFB;
	}
	
	public boolean keywordInTitle(String keyword) {
		keyword = Service.removeAccent(keyword).toUpperCase();
		String bookTitle = Service.removeAccent(this.title).toUpperCase();
		return bookTitle.contains(keyword); 
	}
}
