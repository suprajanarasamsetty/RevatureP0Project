package com.dto;

public class CategoryRequest {
	
	private int CategoryId;
	private String CategoryName;
	private String imageUrl;
	public CategoryRequest(int categoryId, String categoryName, String imageUrl) {
		super();
		CategoryId = categoryId;
		CategoryName = categoryName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getCategoryId() {
		return CategoryId;
	}
	public void setCategoryId(int categoryId) {
		CategoryId = categoryId;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}


}
