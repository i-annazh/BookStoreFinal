package com.why.bookshop.front.service;

public interface CartService {
	public boolean insertCartBook(String name, String bid);
	
	public boolean deleteCartBook(String name, String bid);
}
