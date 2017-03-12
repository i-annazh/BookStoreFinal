package com.why.bookshop.front.service;

import java.util.List;

import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.Category;
import com.why.bookshop.front.entities.Page;

public interface BookService {
	public List<Book> findAllBook();

	public Book findBookById(String bid);

	public Book findBookByName(String bname);

	public List<Book> findTopBook(int top);
	
	public List<Book> findNewBook(int top);
	
	public List<Book> findDiscountBooks(int start, int top);
	
	public List<Category> findBookCategory();
	
	public List<Book> findAllBook(Page page);
	
	public int getTotalPage();
	
	public int getDiscountTotalPage();
	
	public List<Book> findRelativeBook(String bid, int top);
	
}
