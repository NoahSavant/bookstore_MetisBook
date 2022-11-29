package com.metis.book.model;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.*;

import com.metis.book.model.audit.UserDateAudit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Category extends UserDateAudit {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Book> books;

	@OneToOne
	@JoinColumn(name = "image_id", referencedColumnName = "id")
	private Image image;

	public List<Book> getBooks() {
		return books == null ? null : new ArrayList<Book>(this.books);
	}

	public void setBooks(List<Book> books) {
		if(books == null) {
			this.books = null;
		}
		this.books = books;
	}
	
	public String getDomain(){
		String temp = Normalizer.normalize(this.name, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replaceAll("đ", "d").replaceAll("Đ", "D").replaceAll(" ", "");
	}
	
	
}
