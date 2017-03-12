package com.why.bookshop.front.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.Opration;

public class Main {

	private ApplicationContext ac = null;
	private JdbcTemplate jdbcTemplate = null;

	{
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		this.jdbcTemplate = ac.getBean(JdbcTemplate.class);
	}

	@Test
	public void testQuery() {
		RowMapper<Book> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Book.class);
		String sql = "SELECT * FROM t_book a ORDER BY a.`orderBy` DESC LIMIT ?";
		List<Book> books = jdbcTemplate.query(sql, rowMapper, 3);
		System.out.println(books);
	}

	@Test
	public void testConnection() throws Exception {
		System.out.println(ac.getBean(JdbcTemplate.class));
	}
	
	public <T>String createSql(String sql, Map<String, T> wheres) {
		for (Map.Entry<String, T> entry : wheres.entrySet()) {
			Opration opration = (Opration) entry.getValue();
			sql += " and " + entry.getKey() + " " + opration.getOp() + " " + opration.getValue() + " ";
		}
		return sql;
	}
	
	@Test
	public void testCreateSql(){
		String sql = "SELECT * FROM t_book  WHERE 1=1 ";
		Map<String, Opration> map = new HashMap<>();
		map.put("cid", new Opration("=", "'1'"));
		map.put("bname", new Opration("like", "'天'"));
		System.out.println(createSql(sql, map));
	}
}
