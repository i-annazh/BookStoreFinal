package com.why.bookshop.admin.dao;

import java.util.List;

import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.Category;
import com.why.bookshop.front.entities.Page;

public interface AdminManageBook {

	public int updateBook(String sql);

	public int deleteBook(String bid);

	public int addBook(String sql);

	public List<Book> findAllBook(Page page, String sql);

	public List<Category> findBookCategory();
	
	public int getBookTotal();
	
	public Book findBookById(String bid);
}
