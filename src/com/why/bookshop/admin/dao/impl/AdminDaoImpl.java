package com.why.bookshop.admin.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.Category;
import com.why.bookshop.front.entities.Page;

@Repository("baseDao")
public class AdminDaoImpl extends BaseDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findAdmin(String name, String password) {

		String sql = "SELECT COUNT(*) FROM t_admin WHERE adminname = ? and adminpwd = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, name, password);
		return count;
	}

	@Override
	public int updateBook(String sql) {

		return jdbcTemplate.update(sql);
	}

	@Override
	public int deleteBook(String bid) {
		String sql = "DELETE FROM t_cartitem WHERE bid = ?";
		jdbcTemplate.update(sql, bid);
		sql = "DELETE FROM t_book WHERE bid = ?";

		return jdbcTemplate.update(sql, bid);
	}

	@Override
	public int addBook(String sql) {
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<Book> findAllBook(Page page, String sql) {
		RowMapper<Book> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Book.class);
		List<Book> books = jdbcTemplate.query(sql, rowMapper, page.getRow(), Page.PAGE_SIZE / 3);
		return books;
	}

	@Override
	public List<Category> findBookCategory() {
		RowMapper<Category> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Category.class);
		String sql = "SELECT * FROM t_category";
		List<Category> categorys = jdbcTemplate.query(sql, rowMapper);
		return categorys;
	}

	@Override
	public int getBookTotal() {
		String sql = "SELECT COUNT(*) FROM t_book";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public Book findBookById(String bid) {
		RowMapper<Book> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Book.class);
		String sql = "SELECT * FROM t_book WHERE bid = ?";
		Book book = jdbcTemplate.queryForObject(sql, rowMapper, bid);
		return book;
	}

}
