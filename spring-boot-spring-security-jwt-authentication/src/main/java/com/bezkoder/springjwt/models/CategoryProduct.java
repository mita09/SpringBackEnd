package com.bezkoder.springjwt.models;

import java.util.List;

public class CategoryProduct {

	private Category category;

	private List<Product> list;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}

}
