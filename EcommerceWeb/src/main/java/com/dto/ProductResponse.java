package com.dto;

public class ProductResponse {
	
	private int ProductID;
	private String ProductName;
	private double ProductPrice;
	private int SellerID;
	private int CategoryID;
	private String ProductImage;
	private String ProductDescription;
	private int user_id;
	public ProductResponse(int ProductID, String productName, double productPrice, int sellerID, int categoryID,
			String productImage, String productDescription, int user_id) {
		super();
		this.ProductID = ProductID;
		ProductName = productName;
		ProductPrice = productPrice;
		this.SellerID = sellerID;
		this.CategoryID = categoryID;
		ProductImage = productImage;
		ProductDescription = productDescription;
		this.user_id = user_id;
	}
	
	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "ProductResponse [ProductID=" + ProductID + ", ProductName=" + ProductName + ", ProductPrice="
				+ ProductPrice + ", SellerID=" + SellerID + ", CategoryID=" + CategoryID + ", ProductImage="
				+ ProductImage + ", ProductDescription=" + ProductDescription + ", user_id=" + user_id + "]";
	}
	
	
}
