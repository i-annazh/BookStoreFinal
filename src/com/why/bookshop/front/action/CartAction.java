package com.why.bookshop.front.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.Cart;
import com.why.bookshop.front.entities.User;
import com.why.bookshop.front.service.CartService;
import com.why.bookshop.front.service.UserService;

@Component
public class CartAction implements RequestAware, SessionAware, ModelDriven<Book> {

	@Autowired
	private CartService cartService;

	@Autowired
	private UserService userService;

	public String cartDeleteBook() {
		User user = (User) this.session.get("User");
		// 可以用拦截器
		if (user == null) {
			return "myaccount";
		}
		if (cartService.deleteCartBook(user.getLoginname(), this.book.getBid())) {
			Cart cart = new Cart();
			cart.setCount(userService.findCartItemCount(user.getLoginname()));
			cart.setTotal(userService.getCartItemTotal(user.getLoginname()));
			this.session.put("Cart", cart);
			return "delete-success";
		}

		// 返回到失败页面 todo...
		return "";
	}

	public String cartInsertBook() {
		User user = (User) this.session.get("User");
		// 可以用拦截器
		if (user == null) {
			return "myaccount";
		}

		boolean isSuccess = cartService.insertCartBook(user.getLoginname(), this.book.getBid());

		if (isSuccess) {
			Cart cart = new Cart();
			cart.setCount(userService.findCartItemCount(user.getLoginname()));
			cart.setTotal(userService.getCartItemTotal(user.getLoginname()));
			this.session.put("Cart", cart);
			return "insert-success";
		}

		// 失败则 todo...
		return "";
	}

	private Book book;

	@Override
	public Book getModel() {
		// TODO Auto-generated method stub
		book = new Book();
		return book;
	}

	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	private Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
}
