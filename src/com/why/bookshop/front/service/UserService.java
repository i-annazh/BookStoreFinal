package com.why.bookshop.front.service;

import java.util.List;

import com.why.bookshop.front.entities.CartItem;
import com.why.bookshop.front.entities.Page;
import com.why.bookshop.front.entities.User;

public interface UserService {
	public int findUserByName(String name);

	public String findPasswordByName(String name);

	public boolean checkUserLogin(User user);

	public int findCartItemCount(String name);

	public double getCartItemTotal(String name);
	
	public List<CartItem> findCartItemByUserName(String name);
	
	public int getPageTotal(int bookTotal);
	
	public boolean insertUser(User user);
	
}
