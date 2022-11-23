package com.metis.book.utils;

public class Swap {
	public static <T> void swap(T obj1, T obj2) {
		Wrapper<T> wrapper1 = new Wrapper<T>(obj1);
		Wrapper<T> wrapper2 = new Wrapper<T>(obj2);
		T temp;
		temp = wrapper1.innerObject;
		wrapper1.innerObject = wrapper2.innerObject;
		wrapper2.innerObject = temp;
	}
}
