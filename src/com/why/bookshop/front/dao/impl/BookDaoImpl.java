package com.why.bookshop.front.dao.impl;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.why.bookshop.front.dao.BookDao;
import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.Category;
import com.why.bookshop.front.entities.Page;

@Repository("bookDao")
public class BookDaoImpl extends HttpServlet implements BookDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		System.out.println("jdbcTemplate-->" + jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Book> findAllBook() {
		RowMapper<Book> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Book.class);
		String sql = "SELECT * FROM t_book a ORDER BY a.`orderBy` DESC";
		List<Book> books = jdbcTemplate.query(sql, rowMapper);
		return books;
	}

	@Override
	public Book findBookById(String bid) {
		RowMapper<Book> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Book.class);
		String sql = "SELECT * FROM t_book WHERE bid = ?";
		Book book = jdbcTemplate.queryForObject(sql, rowMapper, bid);
		return book;
	}

	@Override
	public Book findBookByName(String bname) {
		RowMapper<Book> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Book.class);
		String sql = "SELECT * FROM t_book WHERE bname like %?%";
		Book book = jdbcTemplate.queryForObject(sql, rowMapper, bname);
		return book;
	}

	@Override
	public List<Book> findTopBook(int top) {
		RowMapper<Book> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Book.class);
		String sql = "SELECT * FROM t_book a ORDER BY a.`orderBy` DESC LIMIT ?";
		List<Book> books = jdbcTemplate.query(sql, rowMapper, top);
		return books;
	}

	@Override
	public List<Book> findNewBooks(int top) {
		RowMapper<Book> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Book.class);
		String sql = "SELECT * FROM t_book WHERE isnew = 1 ORDER BY orderBy DESC LIMIT ?";
		List<Book> books = jdbcTemplate.query(sql, rowMapper, top);
		return books;
	}

	@Override
	public List<Book> findDiscountBooks(int start, int top) {
		RowMapper<Book> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Book.class);
		String sql = "SELECT * FROM t_book ORDER BY discount DESC LIMIT ?, ?";
		List<Book> books = jdbcTemplate.query(sql, rowMapper, start, top);
		return books;
	}

	public Category findBookCategoryByCid(String cid) {
		RowMapper<Category> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Category.class);
		String sql = "SELECT * FROM t_category WHERE cid = ?";
		Category category = jdbcTemplate.queryForObject(sql, rowMapper, cid);
		return category;
	}

	@Override
	public List<Category> findBookCategory() {
		RowMapper<Category> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Category.class);
		String sql = "SELECT * FROM t_category";
		List<Category> categorys = jdbcTemplate.query(sql, rowMapper);
		return categorys;
	}

	@Override
	public List<Book> findAllBook(Page page, String sql) {
		RowMapper<Book> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Book.class);
		List<Book> books = jdbcTemplate.query(sql, rowMapper, page.getRowStart(), Page.PAGE_SIZE);
		return books;
	}

	@Override
	public int getBookTotal() {
		String sql = "SELECT COUNT(*) FROM t_book";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public List<Book> findRelativeBook(String bid, int top) {
		Book book = this.findBookById(bid);
		String cid = book.getCid();
		String sql = "SELECT * FROM t_book a, t_category b WHERE a.cid = b.cid AND a.cid = ? ORDER BY a.orderBy DESC LIMIT 0, ?";
		RowMapper<Book> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Book.class);
		List<Book> books = jdbcTemplate.query(sql, rowMapper, cid, top);
		return books;
	}

	@Override
	public int getDisCountTotal() {
		String sql = "SELECT COUNT(*) FROM t_book WHERE isspecial = 1";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public int getBookCount(String uid, String bid) {
		String sql = "SELECT COUNT(*) FROM t_cartitem WHERE bid = ? AND uid= ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, bid, uid);
		return count;
	}

}
