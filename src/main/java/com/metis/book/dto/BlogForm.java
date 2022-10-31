package com.metis.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogForm{

	private String id;
	private String title;
	private String content;
	private String createBy;
	private String lastUpdateBy;
	private String createDate;
	private String lastUpdateDate;
}
