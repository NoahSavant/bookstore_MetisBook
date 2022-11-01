package com.metis.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.metis.book.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "url")
	private String url;

	@Column(name = "thumbnail_name")
	private String thumbnailName;
	
	@Column(name = "thumbnail_url")
	private String thumbnailURL;
	
	@OneToOne(mappedBy = "image", fetch = FetchType.LAZY)
	private User user;
	
	@OneToOne(mappedBy = "image", fetch = FetchType.LAZY)
	private Book book;
	
	@OneToOne(mappedBy = "image", fetch = FetchType.LAZY)
	private Blog blog;
}
