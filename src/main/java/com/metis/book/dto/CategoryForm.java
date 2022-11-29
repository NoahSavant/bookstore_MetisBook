package com.metis.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryForm {
	private String id;
	private String name;
	private MultipartFile file;
	private String available;
	private String ImageName;
	private String thumbnailName;

	private String createBy;
	private String lastUpdateBy;
	private String createDate;
	private String lastUpdateDate;
}
