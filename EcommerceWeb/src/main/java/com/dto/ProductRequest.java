package com.dto;

public class ProductRequest {
	
	private String ProductName;
	private double ProductPrice;
	private int SellerID;
	private int CategoryID;
	private String ProductImage;
	private String ProductDescription;
	private int user_id;
	
	public ProductRequest(String productName, double productPrice, int sellerID, int categoryID, String ProductImage, String ProductDescription, int user_id)
	{
		super();
		this.ProductName = productName;
		this.ProductPrice = productPrice;
		this.SellerID = sellerID;
		this.CategoryID = categoryID;
		this.ProductImage=ProductImage;
		this.ProductDescription=ProductDescription;
		this.user_id=user_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getProductImage() {
		return ProductImage;
	}

	public void setProductImage(String productImage) {
		ProductImage = productImage;
	}

	public String getProductDescription() {
		return ProductDescription;
	}

	public void setProductDescription(String productDescription) {
		ProductDescription = productDescription;
	}

	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public double getProductPrice() {
		return ProductPrice;
	}
	public void setProductPrice(double productPrice) {
		ProductPrice = productPrice;
	}
	public int getSellerID() {
		return SellerID;
	}
	public void setSellerID(int sellerID) {
		SellerID = sellerID;
	}
	public int getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}
	
}
