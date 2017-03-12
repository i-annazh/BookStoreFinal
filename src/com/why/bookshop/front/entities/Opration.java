package com.why.bookshop.front.entities;

public class Opration {
	private String op;
	private String value;

	public Opration(String op, String value) {
		super();
		this.op = op;
		this.value = value;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
