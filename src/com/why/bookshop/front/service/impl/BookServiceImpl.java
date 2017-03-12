package com.why.bookshop.front.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.why.bookshop.front.dao.BookDao;
import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.Category;
import com.why.bookshop.front.entities.Opration;
import com.why.bookshop.front.entities.Page;
import com.why.bookshop.front.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	{
		System.out.println("BookServiceImpl");
	}

	@Autowired
	private BookDao bookDao;

	public void setBookDao(BookDao bookDao) {
		System.out.println("bookDao-->" + bookDao);
		this.bookDao = bookDao;
	}

	@Override
	public List<Book> findAllBook() {
		// TODO Auto-generated method stub
		return bookDao.findAllBook();
	}

	@Override
	public Book findBookById(String bid) {
		// TODO Auto-generated method stub
		return bookDao.findBookById(bid);
	}

	@Override
	public Book findBookByName(String bname) {
		// TODO Auto-generated method stub
		return bookDao.findBookByName(bname);
	}

	@Override
	public List<Book> findTopBook(int top) {
		// TODO Auto-generated method stub
		return bookDao.findTopBook(top);
	}

	@Override
	public List<Book> findNewBook(int top) {
		return bookDao.findNewBooks(top);
	}

	@Override
	public List<Book> findDiscountBooks(int start, int top) {
		// TODO Auto-generated method stub
		return bookDao.findDiscountBooks(start, top);
	}

	@Override
	public List<Category> findBookCategory() {
		// TODO Auto-generated method stub
		return bookDao.findBookCategory();
	}

	public void setBookCategory(List<Book> books) {
		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			Category category = bookDao.findBookCategoryByCid(book.getCid());
			book.setCategory(category);
		}
	}

	@Override
	public List<Book> findAllBook(Page page) {
		
		
		Map<String, Opration> map = new HashMap<>();
		if (page.getCid() != null) {
			map.put("cid", new Opration("=", "'" + page.getCid() + "'"));
		}
		if (page.getBname() != null) {
			map.put("bname", new Opration("like", "'%" + page.getBname() + "%'"));
		}
		String sql = "SELECT * FROM t_book  WHERE 1=1 ";
		sql = createSql(sql, map) + "ORDER BY orderBy DESC LIMIT ?, ?";
		//System.out.println(sql);
		return bookDao.findAllBook(page, sql);
	}

	public <T> String createSql(String sql, Map<String, T> wheres) {
		for (Map.Entry<String, T> entry : wheres.entrySet()) {
			Opration opration = (Opration) entry.getValue();
			sql += " and " + entry.getKey() + " " + opration.getOp() + " " + opration.getValue() + " ";
		}
		return sql;
	}

	@Override
	public int getTotalPage() {
		int count = bookDao.getBookTotal();

		return calculateSize(count, Page.PAGE_SIZE);
	}

	public int calculateSize(int count, int pageSize) {
		return (int) Math.ceil((float) count / pageSize);
	}

	@Override
	public List<Book> findRelativeBook(String bid, int top) {

		return bookDao.findRelativeBook(bid, top);
	}

	@Override
	public int getDiscountTotalPage() {
		int count = bookDao.getDisCountTotal();
		return calculateSize(count, Page.SPECIALPAGE_SIZE);
	}

}
