package com.metis.book.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogForm{

	private String id;
	private String title;
	private String subTitle;
	private String content;
	private String book;
	private MultipartFile file;
	private String imageName;
	private String createBy;
	private String lastUpdateBy;
	private String createDate;
	private String lastUpdateDate;
}
