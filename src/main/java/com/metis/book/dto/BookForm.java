package com.metis.book.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.metis.book.model.Category;
import com.metis.book.model.Language;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookForm {

	private String id;
	@NotEmpty(message = "Tựa sách không thể trống!")
	@Size(max = 100, message = "Độ dài tựa sách không quá 100 ký tự")
	private String title;
	@Size(max = 100, message = "Độ dài tựa sách không quá 100 ký tự")
	@NotEmpty(message = "Giá không thể trống")
	private String price;
	@Size(max = 100, message = "Độ dài tựa sách không quá 100 ký tự")
	@NotEmpty(message = "Nhà xuất bản không thể trống")
	private String publisherName;
	@Size(max = 100, message = "Độ dài tựa sách không quá 100 ký tự")
	@NotEmpty(message = "Mô tả không thể trống")
	private String description;
	@Size(max = 100, message = "Độ dài tựa sách không quá 100 ký tự")
	@NotEmpty(message = "Ngày xuất bản không thể trống")
	private String publicationDate;
	
	private String language;
	@Size(max = 100, message = "Độ dài tựa sách không quá 100 ký tự")
	@NotEmpty(message = "Số lượng không thể trống")
	private String quantity;
	private Category category;
	
	private MultipartFile file;
	private String available;
	private String ImageName;
	private String thumbnailName;
	private List<String> authors;

	private String createBy;
	private String lastUpdateBy;
	private String createDate;
	private String lastUpdateDate;
	
}
