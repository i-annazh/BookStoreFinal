package com.why.bookshop.front.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.why.bookshop.front.dao.UserDao;
import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.CartItem;
import com.why.bookshop.front.entities.User;
import com.why.bookshop.front.utils.CommonUtils;

@Repository
public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		System.out.println("jdbcTemplate-->" + jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findUserCountByName(String name) {
		String sql = "SELECT COUNT(*) FROM t_user WHERE loginname = '" + name + "'";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public String findPasswordByName(String name) {
		String sql = "SELECT loginpass FROM t_user WHERE loginname = '" + name + "'";
		String pass = jdbcTemplate.queryForObject(sql, String.class);
		return pass;
	}

	@Override
	public int findCartItemCount(String name) {
		String sql = "SELECT COUNT(*) FROM t_user a, t_cartitem b WHERE a.uid = b.uid AND a.loginname = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, name);
		return count;
	}

	@Override
	public double getCartItemTotal(String name) {
		String sql = "SELECT SUM(b.currPrice) FROM t_cartitem a, t_book b, t_user c WHERE a.bid = b.bid AND a.uid = c.uid AND c.loginname = ?";
		Double total = jdbcTemplate.queryForObject(sql, Double.class, name);
		if (total == null) {
			return 0;
		}
		return total;
	}

	@Override
	public User findUserByName(String name) {
		RowMapper<User> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(User.class);
		String sql = "SELECT * FROM t_user a WHERE a.loginname = ?";
		User user = jdbcTemplate.queryForObject(sql, rowMapper, name);
		return user;
	}

	@Override
	public List<CartItem> findCartItemByUserName(String name) {
		RowMapper<CartItem> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(CartItem.class);
		String sql = "SELECT * FROM t_cartitem a, t_book b, t_user c WHERE a.bid = b.bid AND a.uid = c.uid AND c.loginname = ?";
		List<CartItem> cartItems = jdbcTemplate.query(sql, rowMapper, name);
		return cartItems;
	}

	@Override
	public List<Book> findBooksByLoginName(String name) {
		RowMapper<Book> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Book.class);
		String sql = "SELECT * FROM t_cartitem a, t_book b, t_user c WHERE a.bid = b.bid AND a.uid = c.uid AND c.loginname = ?";
		List<Book> books = jdbcTemplate.query(sql, rowMapper, name);
		return books;
	}

	@Override
	public int insertUser(User user) {
		String uid = CommonUtils.getUUID();
		String sql = "INSERT INTO t_user (uid, loginname, loginpass, email) VALUES(?, ?, ?, ?)";
		int result = jdbcTemplate.update(sql, uid, user.getLoginname(), user.getLoginpass(), user.getEmail());
		return result;
	}

}
