package com.why.bookshop.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.why.bookshop.admin.dao.impl.BaseDao;
import com.why.bookshop.admin.entities.Admin;
import com.why.bookshop.admin.utils.BeanUtils;
import com.why.bookshop.admin.utils.CommonUtils;
import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.Category;
import com.why.bookshop.front.entities.Opration;
import com.why.bookshop.front.entities.Page;

@Service("baseService")
public class AdminServiceImpl extends BaseService {
	@Autowired
	private BaseDao baseDao;

	@Override
	public boolean isAdmin(Admin admin) {
		System.out.println(admin);
		int adminCount = baseDao.findAdmin(admin.getAdminName(), admin.getAdminPwd());
		if (adminCount == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<Category> findBookCategory() {
		// TODO Auto-generated method stub
		return baseDao.findBookCategory();
	}

	public <T> String createSql(String sql, Map<String, T> wheres) {
		for (Map.Entry<String, T> entry : wheres.entrySet()) {
			Opration opration = (Opration) entry.getValue();
			sql += " and " + entry.getKey() + " " + opration.getOp() + " " + opration.getValue() + " ";
		}
		return sql;
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

		List<Book> books = baseDao.findAllBook(page, sql);
		return subBookName(books);
	}

	private List<Book> subBookName(List<Book> books) {
		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			if (book.getBname().length() > 20)
				book.setBname(book.getBname().substring(0, 20) + "....");
		}
		return books;
	}

	@Override
	public int getTotalPage() {
		int count = baseDao.getBookTotal();

		return calculateSize(count, Page.PAGE_SIZE / 3);
	}

	public int calculateSize(int count, int pageSize) {
		return (int) Math.ceil((float) count / pageSize);
	}
	
	@Override
	public boolean deleteBook(String bid) {
		return baseDao.deleteBook(bid) > 0;
	}

	@Override
	public boolean insertBook(Book book) {
		Map<String, Object> map = BeanUtils.toMap(book);
		String sql = CommonUtils.createInsertSQL("t_book", map);
		return baseDao.addBook(sql) > 0;
	}

	@Override
	public Book findBookByBid(String bid) {
		// TODO Auto-generated method stub
		return baseDao.findBookById(bid);
	}

	@Override
	public boolean updateBook(Book book) {
		Map<String, Object> map = BeanUtils.toMap(book);
		String wheres = " where bid = '" + book.getBid() + "'";
		String sql = CommonUtils.createUpdateSQL(wheres, "t_book", map);
		System.out.println("--update book -->>" + sql);
		return baseDao.updateBook(sql) > 0;
	}

}
