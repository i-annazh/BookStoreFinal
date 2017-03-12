package com.why.bookshop.front.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.why.bookshop.front.dao.BookDao;
import com.why.bookshop.front.dao.UserDao;
import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.CartItem;
import com.why.bookshop.front.entities.Page;
import com.why.bookshop.front.entities.User;
import com.why.bookshop.front.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public int findUserByName(String name) {

		return userDao.findUserCountByName(name);
	}

	@Override
	public String findPasswordByName(String name) {
		// TODO Auto-generated method stub
		return userDao.findPasswordByName(name);
	}

	@Override
	public boolean checkUserLogin(User user) {
		int userCount = this.findUserByName(user.getLoginname());
		if (userCount != 1) {
			return false;
		}
		String password = this.findPasswordByName(user.getLoginname());
		if (password.trim().equals(user.getLoginpass().trim())) {
			return true;
		}
		return false;
	}

	@Override
	public int findCartItemCount(String name) {
		// TODO Auto-generated method stub
		return userDao.findCartItemCount(name);
	}

	@Override
	public double getCartItemTotal(String name) {
		// TODO Auto-generated method stub
		return userDao.getCartItemTotal(name);
	}

	/**
	 * 得到分页后的页数
	 */
	@Override
	public int getPageTotal(int bookTotal) {
		return (int) Math.ceil((double) bookTotal / Page.CARTPAGE_SIZE);
	}

	@Override
	public List<CartItem> findCartItemByUserName(String name) {
		// TODO Auto-generated method stub
		User user = userDao.findUserByName(name);
		List<CartItem> cartItems = userDao.findCartItemByUserName(name);
		List<Book> books = userDao.findBooksByLoginName(name);
		subBookName(books);

		setBookAndUser(cartItems, user, books);

		System.out.println(cartItems);
		return cartItems;
	}

	public void setBookAndUser(List<CartItem> cartItems, User user, List<Book> books) {
		for (int i = 0; i < cartItems.size(); i++) {
			cartItems.get(i).setBook(books.get(i));
			cartItems.get(i).setUser(user);
		}
	}

	public List<Book> subBookName(List<Book> books) {
		for (Book book : books) {
			book.setBname(book.getBname().substring(0, 8));
		}
		return books;
	}

	@Override
	public boolean insertUser(User user) {
		int result = userDao.insertUser(user);
		return result > 0;
	}

}
