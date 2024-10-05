package com.dto;

import java.time.LocalDateTime;

public class OrdersResponse {
	private int Order_id;
	private int user_id;
	private String ShippingAddress;
	private String BillingAddress;
	private LocalDateTime Order_Date=LocalDateTime.now();
	private String Order_Status;
	private int ProductID;
	
	public OrdersResponse(int order_id, int user_id, String shippingAddress, String billingAddress,
			LocalDateTime orderDate, String order_Status, int ProductID) {
		super();
		Order_id = order_id;
		this.user_id = user_id;
		ShippingAddress = shippingAddress;
		BillingAddress = billingAddress;
		Order_Date = LocalDateTime.now();
		Order_Status = order_Status;
		this.ProductID=ProductID;
	}
	public OrdersResponse() {
		// TODO Auto-generated constructor stub
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public int getOrder_id() {
		return Order_id;
	}
	public void setOrder_id(int order_id) {
		Order_id = order_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getShippingAddress() {
		return ShippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		ShippingAddress = shippingAddress;
	}
	public String getBillingAddress() {
		return BillingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		BillingAddress = billingAddress;
	}
	public LocalDateTime getOrder_Date() {
		return Order_Date;
	}
	public void setOrder_Date(LocalDateTime order_Date) {
		Order_Date = order_Date;
	}
	public String getOrder_Status() {
		return Order_Status;
	}
	public void setOrder_Status(String order_Status) {
		Order_Status = order_Status;
	}
	@Override
	public String toString() {
		return "OrdersResponse [Order_id=" + Order_id + ", user_id=" + user_id + ", ShippingAddress=" + ShippingAddress
				+ ", BillingAddress=" + BillingAddress + ", Order_Date=" + Order_Date + ", Order_Status=" + Order_Status
				+ "]";
	}
	

}
