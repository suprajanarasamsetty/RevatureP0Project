package com.entity;

public class Seller {
	private int SellerID;
	private String SellerName;
	private String SellerEmail;
	private String SellerPassword;
	private String BusinessName;
	private String BusinessDetails;
	
	public Seller(int sellerID, String sellerName, String sellerEmail, String sellerPassword, String businessName,
			String businessDetails) {
		super();
		SellerID = sellerID;
		SellerName = sellerName;
		SellerEmail = sellerEmail;
		SellerPassword = sellerPassword;
		BusinessName = businessName;
		BusinessDetails = businessDetails;
	}
	public int getSellerID() {
		return SellerID;
	}
	public void setSellerID(int sellerID) {
		SellerID = sellerID;
	}
	public String getSellerName() {
		return SellerName;
	}
	public void setSellerName(String sellerName) {
		SellerName = sellerName;
	}
	public String getSellerEmail() {
		return SellerEmail;
	}
	public void setSellerEmail(String sellerEmail) {
		SellerEmail = sellerEmail;
	}
	public String getSellerPassword() {
		return SellerPassword;
	}
	public void setSellerPassword(String sellerPassword) {
		SellerPassword = sellerPassword;
	}
	public String getBusinessName() {
		return BusinessName;
	}
	public void setBusinessName(String businessName) {
		BusinessName = businessName;
	}
	public String getBusinessDetails() {
		return BusinessDetails;
	}
	public void setBusinessDetails(String businessDetails) {
		BusinessDetails = businessDetails;
	}
	
}
