package com.dto;

public class SellerResponse {
	
	private int SellerID;
	private String SellerName;
	private String SellerEmail;
	private String SellerPassword;
	private String BusinessName;
	private String BusinessDetails;
	private int user_id;
	
	
	public SellerResponse(int sellerID, String sellerName, String sellerEmail, String sellerPassword,
			String businessName, String businessDetails, int user_id) {
		super();
		this.SellerID = sellerID;
		this.SellerName = sellerName;
		this.SellerEmail = sellerEmail;
		this.SellerPassword = sellerPassword;
		this.BusinessName = businessName;
		this.BusinessDetails = businessDetails;
		this.user_id = user_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	@Override
	public String toString() {
		return "SellerResponse [SellerID=" + SellerID + ", SellerName=" + SellerName + ", SellerEmail=" + SellerEmail
				+ ", SellerPassword=" + SellerPassword + ", BusinessName=" + BusinessName + ", BusinessDetails="
				+ BusinessDetails + ", user_id=" + user_id + "]";
	}
	
}
