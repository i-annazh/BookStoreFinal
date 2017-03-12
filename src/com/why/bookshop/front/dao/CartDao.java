package com.why.bookshop.front.dao;

public interface CartDao {
	public int deleteCartBookByBid(String name, String bid);
	
	public int insertCartBook(String name, String bid);

	public int updateCartBook(String name, String bid);
}
