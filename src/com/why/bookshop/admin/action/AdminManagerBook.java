package com.why.bookshop.admin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.why.bookshop.admin.service.impl.BaseService;
import com.why.bookshop.admin.utils.CommonUtils;
import com.why.bookshop.front.entities.Book;
import com.why.bookshop.front.entities.Category;
import com.why.bookshop.front.entities.Page;

public class AdminManagerBook extends ActionSupport
		implements ParameterAware, RequestAware, ModelDriven<Book>, Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private BaseService baseService;

	private Page page;

	// Book 对象
	private Book book;

	public String viewBook() {
		page = new Page();
		if (this.params.get("currPage") != null)
			page.setCurrPage(Integer.parseInt(this.params.get("currPage")[0].toString()));

		try {
			if (this.params.get("bname") != null) {
				// 解决中文乱码
				String bname = new String(this.params.get("bname")[0].getBytes("iso-8859-1"), "utf-8");
				this.page.setBname(bname);
				System.out.println(bname);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("books---" + baseService.findAllBook(page).size());
		this.request.put("books", baseService.findAllBook(page));
		page.setTotal(baseService.getTotalPage());
		this.request.put("page", page);
		return "view";
	}

	public String deleteBook() {
		if (baseService.deleteBook(book.getBid())) {
			return "delete_success";
		}
		// todo...
		return "delete_failt";
	}

	public void prepareDeleteBook() {
		book = new Book();
	}

	public String addBook() {
		// 分类的下拉列表数据
		setCategory();

		if (this.image_bb == null) { // 首次访问
			this.request.put("action", "add");
			this.request.put("msg", "请填完整以及确保类型是否正确!!!");
			return "edit_page";
		}

		// 填满图书的信息. 图片...
		fillBookMessage();

		// System.out.println("--addBook-->>" + this.book);

		// 插入图书
		if (baseService.insertBook(book)) {
			this.request.put("msg", "插入成功");
		}

		this.request.put("action", "add");
		return "edit_page";
	}

	private void fillBookMessage() {

		// 搜集图书的信息
		File image_w = this.image_bb.get(0);
		String path = ServletActionContext.getRequest().getServletContext().getRealPath("/jsps/book_img");

		String pname = CommonUtils.getUUID();
		String fileName = path + "/" + pname + ".jpg";

		String image_wName = "book_img/" + pname + ".jpg";
		// 这里 todo...
		String image_bName = image_wName;
		String bid = CommonUtils.getUUID();
		this.book.setBid(bid);
		this.book.setPaper("胶质纸");
		this.book.setImage_b(image_bName);
		this.book.setImage_w(image_wName);
		this.book.setOrderBy(1000);
		this.book.setPrice(book.getCurrPrice() / book.getDiscount() * 10);
		saveFile(image_w, fileName);
	}

	private void setCategory() {
		List<Category> categorys = baseService.findBookCategory();
		this.request.put("categorys", categorys);
	}

	private void saveFile(File image_w, String fileName) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(image_w);
			fos = new FileOutputStream(fileName);
			int len = -1;
			byte[] b = new byte[1024];
			while ((len = fis.read(b)) != -1) {
				fos.write(b, 0, len);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void prepareAddBook() {
		book = new Book();
	}

	public String updateBook() {
		// 分类的下拉列表数据
		setCategory();

		if (this.params.get("action") == null && this.params.get("bid") != null) {

			this.request.put("msg", "请填完整以及确保类型是否正确!!!");

			// 如果是更新图书则带上图书的 id
			this.request.put("action", "update?action=submit&bid=" + this.params.get("bid")[0]);
			this.request.put("book", this.book);
			return "edit_page";
		} else if (this.params.get("action") != null) { // 说明是提交表单
			if (this.image_bb == null) { // 没有选择图片
				this.request.put("msg", "请选择图片");
				this.request.put("book", this.book);
				
				//
				this.request.put("action", "update?action=submit&bid=" + this.params.get("bid")[0]);
				return "edit_page";
			}
		}
		String bid = book.getBid();
		fillBookMessage();
		// 更新图书
		book.setBid(bid);
		baseService.updateBook(book);

		return "edit_page_redirect";
	}

	public void prepareUpdateBook() {
		if (this.params.get("action") != null) {
			book = new Book();
		} else if (this.params.get("bid") != null) {
			this.book = baseService.findBookByBid(this.params.get("bid")[0]);
		}

	}

	@Override
	public Book getModel() {
		return book;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("praable----");
	}

	private Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	/**
	 * 上传文件相关
	 */
	private List<File> image_ww;
	private List<String> image_wwContentType;
	private List<String> image_wwFileName;
	private List<String> image_wwDesc;
	private List<File> image_bb;
	private List<String> image_bbContentType;
	private List<String> image_bbFileName;
	private List<String> image_bbDesc;

	public List<File> getImage_ww() {
		return image_ww;
	}

	public void setImage_ww(List<File> image_ww) {
		this.image_ww = image_ww;
	}

	public List<String> getImage_wwContentType() {
		return image_wwContentType;
	}

	public void setImage_wwContentType(List<String> image_wwContentType) {
		this.image_wwContentType = image_wwContentType;
	}

	public List<String> getImage_wwFileName() {
		return image_wwFileName;
	}

	public void setImage_wwFileName(List<String> image_wwFileName) {
		this.image_wwFileName = image_wwFileName;
	}

	public List<String> getImage_wwDesc() {
		return image_wwDesc;
	}

	public void setImage_wwDesc(List<String> image_wwDesc) {
		this.image_wwDesc = image_wwDesc;
	}

	public List<File> getImage_bb() {
		return image_bb;
	}

	public void setImage_bb(List<File> image_bb) {
		this.image_bb = image_bb;
	}

	public List<String> getImage_bbContentType() {
		return image_bbContentType;
	}

	public void setImage_bbContentType(List<String> image_bbContentType) {
		this.image_bbContentType = image_bbContentType;
	}

	public List<String> getImage_bbFileName() {
		return image_bbFileName;
	}

	public void setImage_bbFileName(List<String> image_bbFileName) {
		this.image_bbFileName = image_bbFileName;
	}

	public List<String> getImage_bbDesc() {
		return image_bbDesc;
	}

	public void setImage_bbDesc(List<String> image_bbDesc) {
		this.image_bbDesc = image_bbDesc;
	}

	private Map<String, String[]> params;

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		// TODO Auto-generated method stub
		this.params = arg0;
	}

}
