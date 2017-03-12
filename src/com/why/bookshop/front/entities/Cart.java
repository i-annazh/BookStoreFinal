package com.why.bookshop.front.entities;

public class Cart {
	private int count;
	private Double total;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Cart [count=" + count + ", total=" + total + "]";
	}

}
