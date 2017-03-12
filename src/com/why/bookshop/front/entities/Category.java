package com.why.bookshop.front.entities;

public class Category {
	private String cid;
	private String cname;
	private String desc;
	private int orderBy;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + ", desc=" + desc + ", orderBy=" + orderBy + "]";
	}

}
