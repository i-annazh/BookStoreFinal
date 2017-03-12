package com.why.bookshop.admin.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class CommonUtils {
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString();
		String[] strs = uuid.split("-");
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < strs.length; i++){
			sb.append(strs[i]);
		}
		
		return sb.toString().toUpperCase();
	}
	
	public static String createUpdateSQL(String wheres, String tableName, Map<String, Object> map){
		StringBuilder sql = new StringBuilder("update " + tableName +" set ");
		for(Map.Entry<String, Object> entry : map.entrySet()){
			sql.append(entry.getKey()).append("=").append("'")
					.append(entry.getValue()).append("'").append(",");
		}
		sql = new StringBuilder(sql.substring(0, sql.length() - 1));
		sql.append(wheres).append(";");
		System.out.println(sql);
		return sql.toString();
	}
	
	public static String createInsertSQL(String tableName, Map<String, Object> map){
		String sql = "insert into " + tableName +"(";
		StringBuilder sb = new StringBuilder();
		StringBuilder values = new StringBuilder(" values( ");
		for(Map.Entry<String, Object> entry : map.entrySet()){
			sb.append(entry.getKey()).append(",");
			values.append("'").append(entry.getValue()).append("'").append(",");
		}
		sb = new StringBuilder(sb.substring(0, sb.length() - 1));
		sb.append(")");
		values = new StringBuilder(values.substring(0, values.length() - 1));
		values.append(");");

		return sql + sb.toString() +  values.toString();
	}
}
