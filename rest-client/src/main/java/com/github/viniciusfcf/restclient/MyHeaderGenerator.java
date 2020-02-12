package com.github.viniciusfcf.restclient;

public class MyHeaderGenerator {
	public static String generateCustomHeader(String headerName) {
		if ("CustomHeader".equals(headerName)) {
			throw new UnsupportedOperationException();
		}
		return "SomeValue";
	}
}