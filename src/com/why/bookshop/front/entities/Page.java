package com.why.bookshop.front.entities;

public class Page {
	public static final int PAGE_SIZE = 15;
	public static final int SPECIALPAGE_SIZE = 4;
	public static final int CARTPAGE_SIZE = 6;
	private int currPage = 1;
	private String cid;
	private String bname;
	private int total;
	private String bid;

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getRow() {
		return (this.currPage - 1) * PAGE_SIZE / 3;
	}
	
	public int getRowStart(){
		return (this.currPage - 1) * PAGE_SIZE;
	}

	@Override
	public String toString() {
		return "Page [currPage=" + currPage + ", cid=" + cid + ", bname=" + bname + "]";
	}

}
