package com.why.bookshop.admin.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtils {
	public static <T> Map<String, Object> toMap(T t) {
		Map<String, Object> map = new HashMap<>();
		Field[] fields = t.getClass().getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			try {
				fields[i].setAccessible(true);
				
				String fieldType = fields[i].getType().getName();
				if(fieldType.equals("java.lang.String")
						||fieldType.equals("double")
						||fieldType.equals("int")){
					String fieldName = fields[i].getName();
					Object fieldValue = fields[i].get(t);
					map.put(fieldName, fieldValue);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

		return map;
	}
}
