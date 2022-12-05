package com.metis.book.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Service {
	public static String removeAccent(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replaceAll("đ", "d").replaceAll("Đ", "D").replaceAll(" ", "");
	}
}
