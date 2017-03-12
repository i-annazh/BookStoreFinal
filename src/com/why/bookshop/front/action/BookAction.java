package com.why.bookshop.front.action;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.CartItem;
import com.why.bookshop.front.entities.Category;
import com.why.bookshop.front.entities.Page;
import com.why.bookshop.front.entities.User;
import com.why.bookshop.front.service.BookService;
import com.why.bookshop.front.service.UserService;

@Component
public class BookAction implements RequestAware, SessionAware, ParameterAware, ModelDriven<Page> {
	private BookService bookService;

	@Autowired
	private UserService userService;

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	/**
	 * 为页面设置图书的相关信息
	 */
	public void setBooks() {
		List<Book> books = bookService.findTopBook(2);
		List<Book> newBooks = bookService.findNewBook(6);
		List<Book> discountBooks = bookService.findDiscountBooks(0, 4);
		List<Category> categorys = bookService.findBookCategory();
		this.request.put("categorys", categorys);
		this.request.put("books", books);
		this.request.put("newBooks", newBooks);
		this.request.put("discountBooks", discountBooks);
	}

	public String viewIndexPage() {
		setBooks();
		return "index";
	}

	public String viewAboutUs() {
		setBooks();
		return "about";
	}

	public void setPage(int total) {
		Page newPage = new Page();
		newPage.setBname(this.page.getBname());
		newPage.setCurrPage(this.page.getCurrPage() - 1);
		newPage.setTotal(total);
		this.request.put("newPage", newPage);
	}

	public String viewBooks() {
		setBooks();

		try {
			if (page.getBname() != null) {
				// 解决中文乱码
				String bname = new String(page.getBname().getBytes("iso-8859-1"), "utf-8");
				this.page.setBname(bname);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 分页查找图书
		List<Book> pageBooks = bookService.findAllBook(this.page);
		// 设置Page相关属性
		setPage(bookService.getTotalPage());

		this.request.put("pageBooks", pageBooks);
		return "category";
	}

	public String viewSpecialBooks() {
		setBooks();
		setPage(bookService.getDiscountTotalPage());
		this.request.put("specialBooks", bookService.findDiscountBooks(this.page.getCurrPage(), Page.SPECIALPAGE_SIZE));
		return "specials";
	}

	public String viewMyAccount() {
		setBooks();
		return "myaccount";
	}

	public String viewRegister() {
		setBooks();
		return "register";
	}

	public String viewPrice() {
		setBooks();

		if (this.parameter.get("bid") == null) {
			return "details";
		}
		Book book = bookService.findBookById(this.page.getBid());
		// 显示相关图书
		List<Book> relativeBooks = bookService.findRelativeBook(this.page.getBid(), 9);
		this.request.put("relativeBooks", relativeBooks);
		this.request.put("book", book);
		return "details";
	}

	public String viewContact() {
		setBooks();
		return "contact";
	}

	public String viewCart() {
		setBooks();
		User user = (User) this.session.get("User");
		if (user == null) {
			return "myaccount";
		}
		List<CartItem> cartItem = userService.findCartItemByUserName(user.getLoginname());
		System.out.println(cartItem);
		this.request.put("cartItems", cartItem);
		int pageTotal = userService.getPageTotal(userService.findCartItemCount(user.getLoginname()));
		setPage(pageTotal);
		return "cart";
	}

	private Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;

	}

	public Map<String, String[]> parameter;

	@Override
	public void setParameters(Map<String, String[]> parameter) {
		this.parameter = parameter;
	}

	private Page page;

	@Override
	public Page getModel() {
		page = new Page();
		return page;
	}

	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

}
