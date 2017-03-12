package com.why.bookshop.front.dao;

import java.util.List;

import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.Category;
import com.why.bookshop.front.entities.Page;

public interface BookDao {
	public List<Book> findAllBook();

	public Book findBookById(String bid);

	public Book findBookByName(String bname);

	public List<Book> findTopBook(int top);
	
	public List<Book> findNewBooks(int top);
	
	public List<Book> findDiscountBooks(int start, int top);

	public List<Category> findBookCategory();

	public Category findBookCategoryByCid(String cid);

	public List<Book> findAllBook(Page page, String sql);
	
	public int getBookTotal();
	
	public List<Book> findRelativeBook(String bid, int top);

	public int getDisCountTotal();
	
	public int getBookCount(String uid, String bid);
	
}
