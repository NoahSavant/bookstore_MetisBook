package com.metis.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.metis.book.model.audit.UserDateAudit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blogs")
public class Blog extends UserDateAudit {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	@Lob
	private String title;
	
	@Column(name = "sub_title")
	@Lob
	private String subTitle;
	
	@Column(name = "content")
	@Lob
	private String content;
	
	@OneToOne
	@JoinColumn(name = "image_id", referencedColumnName = "id")
	private Image image;
	
	@Transient
	private String createdUser;
	
	@Transient
	private String updatedUser;
	
	@OneToOne
	@JoinColumn(name="book_id", referencedColumnName = "id")
	private Book book;



	
	
	
}
