package com.why.bookshop.front.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.why.bookshop.front.dao.BookDao;
import com.why.bookshop.front.dao.CartDao;
import com.why.bookshop.front.dao.UserDao;
import com.why.bookshop.front.entities.User;
import com.why.bookshop.front.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public boolean insertCartBook(String name, String bid) {
		User user = userDao.findUserByName(name);
		int bookCount = bookDao.getBookCount(user.getUid(), bid);
		int result = 0;
		System.out.println("bookCount" + bookCount);
		if (bookCount > 0) {
			result = cartDao.updateCartBook(name, bid);
		} else {
			
			result = cartDao.insertCartBook(name, bid);
		}
		return result > 0;
	}

	@Override
	public boolean deleteCartBook(String name, String bid) {
		int result = cartDao.deleteCartBookByBid(name, bid);
		return result > 0;
	}

}
