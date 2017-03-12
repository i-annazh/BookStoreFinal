package com.why.bookshop.admin.action;

import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.tags.ParamAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.why.bookshop.admin.entities.Admin;
import com.why.bookshop.admin.service.AdminService;
import com.why.bookshop.front.action.BookAction;
import com.why.bookshop.front.service.BookService;
import com.why.bookshop.front.service.UserService;

@Component
public class AdminLoginAction extends ActionSupport
		implements RequestAware, SessionAware, ModelDriven<Admin>, ParameterAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private AdminService adminService;
	
	public String adminValidation() {
		if (isValidateCode()) { // 如果验证码正确, 再去数据库查询用户名密码是否正确
			if (adminService.isAdmin(admin)) {
				//保存Admin到Session中
				this.session.put("admin", admin.getAdminName());
				return "login_success";
			} else {
				this.request.put("loginMsg", "用户名或密码错误");
			}
		} else {
			this.request.put("loginMsg", "验证码错误");
		}
		this.session.remove("admin");
		return "login_failt";
	}

	public boolean isValidateCode() {
		String vcode = this.session.get("vcode").toString();
		String myVcode = this.params.get("vcode")[0];
		return vcode.equalsIgnoreCase(myVcode);
	}

	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	private Admin admin;

	@Override
	public Admin getModel() {
		admin = new Admin();
		return admin;
	}

	private Map<String, String[]> params;

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		// TODO Auto-generated method stub
		this.params = arg0;
	}

	private Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

}
