package com.why.bookshop.front.action;

import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.why.bookshop.front.entities.Cart;
import com.why.bookshop.front.entities.User;
import com.why.bookshop.front.service.UserService;

@Component
public class UserAction implements RequestAware, SessionAware, ParameterAware, ModelDriven<User> {
	@Autowired
	private UserService userService;

	private Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;

	}
	
	public String userRegister(){
		if(userService.findUserByName(this.user.getLoginname()) > 0){
			
			this.session.put("RegisterMsg", "此用户已存在!!!");
			return "register-failt";
		}
		
		if(userService.insertUser(this.user)){
			this.session.put("RegisterMsg", "注册成功");
			return "register-success";
		}
		
		// todo...
		return "";
	}
	
	public String userLogin() {
		boolean isLogin = userService.checkUserLogin(this.user);
		// 保存 User 到 Session内置对象中
		if (isLogin) {
			this.session.put("User", this.user);
			Cart cart = new Cart();
			cart.setCount(userService.findCartItemCount(this.user.getLoginname()));
			cart.setTotal(userService.getCartItemTotal(this.user.getLoginname()));
			this.session.put("Cart", cart);
		} else {
			this.request.put("ErrorMsg", "用户名或密码错误!");
			return "login_failt";
		}

		return "login_success";
	}

	public String userCancle() {
		System.out.println(this.user.getLoginname());
		this.session.remove("User");
		this.session.remove("RegisterMsg");
		this.session.remove("Cart");
		return "login_cancle";
	}

	private User user;

	@Override
	public User getModel() {
		this.user = new User();
		return user;
	}

	private Map<String, String[]> parameters;

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.parameters = arg0;

	}

	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

}
