package com.why.bookshop.admin.service;

import java.util.List;

import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.Category;
import com.why.bookshop.front.entities.Page;

public interface AdminManageBookService {
	public List<Book> findAllBook(Page page);

	public List<Category> findBookCategory();
	
	public int getTotalPage() ;
	
	public boolean deleteBook(String bid);
	
	public boolean insertBook(Book book);
	
	public boolean updateBook(Book book);
	
	public Book findBookByBid(String bid);
}
