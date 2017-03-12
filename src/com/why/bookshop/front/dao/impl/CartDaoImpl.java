package com.why.bookshop.front.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.why.bookshop.front.dao.BookDao;
import com.why.bookshop.front.dao.CartDao;
import com.why.bookshop.front.dao.UserDao;
import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.CartItem;
import com.why.bookshop.front.entities.User;
import com.why.bookshop.front.utils.CommonUtils;

@Repository
public class CartDaoImpl implements CartDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private BookDao bookDao;
	@Autowired
	private UserDao userDao;

	@Override
	public int deleteCartBookByBid(String name, String bid) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM t_cartitem WHERE bid = ?";
		Object[] obj = new Object[] { bid };
		int result = jdbcTemplate.update(sql, obj);// 可以传两个参数，第一个参数是SQL语句，第二个参数是SQL语句的参数值
		return result;
	}

	@Override
	public int insertCartBook(String name, String bid) {
		Book book = bookDao.findBookById(bid);
		User user = userDao.findUserByName(name);
		String cartItemId = CommonUtils.getUUID();
		System.out.println("end");
		String sql = "INSERT INTO t_cartitem(cartItemId,quantity,bid,uid,orderBy) VALUES (?,?,?,?,?)";

		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[] { cartItemId, 1, book.getBid(), user.getUid(), 100 });

		int[] result = jdbcTemplate.batchUpdate(sql, batchArgs);
		return result[0];
	}

	@Override
	public int updateCartBook(String name, String bid) {
		String sql = "UPDATE t_cartitem a, t_user b SET quantity = quantity + 1 WHERE a.uid = b.uid AND b.loginname = ? AND a.bid = ?";
		int result = jdbcTemplate.update(sql, name, bid);
		return result;
	}

}
