package com.metis.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookRequest")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookRequest {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name",nullable = false, length = 40)
	private String name;
	
	@Column(name = "email",nullable = false, length = 40)
	private String email;
	
	@Column(name = "title",nullable = false, length = 40)
	private String title;
	
	@Column(name = "author",nullable = false, length = 200)
	private String author;
}
