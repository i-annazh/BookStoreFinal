package com.why.bookshop.front.dao;

import java.util.List;

import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.CartItem;
import com.why.bookshop.front.entities.User;

public interface UserDao {
	public int findUserCountByName(String name);
	
	public User findUserByName(String name);

	public String findPasswordByName(String name);

	public int findCartItemCount(String name);

	public double getCartItemTotal(String name);

	public List<CartItem> findCartItemByUserName(String name);

	public List<Book> findBooksByLoginName(String name);
	
	public int insertUser(User user);
}
