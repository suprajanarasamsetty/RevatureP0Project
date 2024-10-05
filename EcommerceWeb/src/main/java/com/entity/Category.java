package com.entity;

public class Category {
	
	private String CategoryName;
	private int CategoryId;
	public Category(String categoryName, int categoryId) {
		super();
		CategoryName = categoryName;
		CategoryId = categoryId;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public int getCategoryId() {
		return CategoryId;
	}
	public void setCategoryId(int categoryId) {
		CategoryId = categoryId;
	}
	
	

}
