package com.why.bookshop.front.utils;

import java.util.UUID;

import org.junit.Test;

public class CommonUtils {

	public static String trimUUID(String string) {
		String[] strs = string.split("-");

		StringBuilder sb = new StringBuilder();
		for (String str : strs) {
			sb.append(str);
		}
		return sb.toString().toUpperCase();
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return trimUUID(uuid.toString());
	}

	@Test
	public void testUUID() {
		System.out.println(getUUID());
	}

}
