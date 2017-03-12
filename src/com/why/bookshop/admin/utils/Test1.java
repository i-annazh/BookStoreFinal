package com.why.bookshop.admin.utils;

import org.junit.Test;

import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.Category;

public class Test1 {
	
	@Test
	public void testCreateUpdateSQL(){
		Book book = new Book();
		book.setAuthor("Hanyu");
		book.setBid("dsadas");
		Category category = new Category();
		category.setCid("AAA");
		book.setCategory(category);
		book.setCid("BBB");
		
		System.out.println(CommonUtils.createUpdateSQL(" where bid = AAA", "t_book", BeanUtils.toMap(book)));
	}
	
	@Test
	public void testCreateInsertSQL(){
		Book book = new Book();
		book.setAuthor("Hanyu");
		book.setBid("dsadas");
		Category category = new Category();
		category.setCid("AAA");
		book.setCategory(category);
		book.setCid("BBB");
		
		CommonUtils.createInsertSQL("t_book", BeanUtils.toMap(book));
	}
	
	@Test
	public void testBeanUtils(){
		Book book = new Book();
		book.setAuthor("Hanyu");
		book.setBid("dsadas");
		Category category = new Category();
		category.setCid("AAA");
		book.setCategory(category);
		book.setCid("BBB");
		System.out.println(BeanUtils.toMap(book));
	}
	
	@Test
	public void testUUID(){
		System.out.println(CommonUtils.getUUID());
	}
}
