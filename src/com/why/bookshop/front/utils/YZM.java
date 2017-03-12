package com.why.bookshop.front.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class YZM extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String contentType;
	private long contentLength;
	private String contentDisposition;
	private InputStream inputStream = null;

	public String getContentType() {
		return contentType;
	}

	public long getContentLength() {
		return contentLength;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getValicode() throws ServletException, IOException {

		VCode v = new VCode();
		BufferedImage img = v.createImage();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageOutputStream imOut = null;

		try {
			imOut = ImageIO.createImageOutputStream(baos);

			ImageIO.write(img, "png", imOut);

			inputStream = new ByteArrayInputStream(baos.toByteArray());

		} catch (IOException e) {
			e.printStackTrace();
		}
		// 保存验证码到 Session 中
		this.session.put("vcode", v.getCode());
		
		contentType = "text/html";
		contentLength = inputStream.available();

		return SUCCESS;
	}

	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
}
