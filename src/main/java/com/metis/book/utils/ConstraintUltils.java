package com.metis.book.utils;

import java.util.regex.Pattern;

public class ConstraintUltils {

	public static Boolean isContainSpecialChar(String username) {

		Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]"); // fill in any chars that you consider special

		if (regex.matcher(username).find()) {
			return true;
		}
		return false;
	}
}
