package com.metis.book.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterForm {
	private Long maxPrice;
	private Long minPrice;
	private String publisherName;
	private String sort;
	private String textSearch;
}
