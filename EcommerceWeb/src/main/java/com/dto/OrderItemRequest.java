package com.dto;

public class OrderItemRequest {
	
	private int OrderItemID;
	private int Order_id;
	private int ProductID;
	private int Quantity;
	private double Price;
	public OrderItemRequest(int orderItemID, int order_id, int productID, int quantity, double price) {
		super();
		OrderItemID = orderItemID;
		Order_id = order_id;
		ProductID = productID;
		Quantity = quantity;
		Price = price;
	}
	public int getOrderItemID() {
		return OrderItemID;
	}
	public void setOrderItemID(int orderItemID) {
		OrderItemID = orderItemID;
	}
	public int getOrder_id() {
		return Order_id;
	}
	public void setOrder_id(int order_id) {
		Order_id = order_id;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	

}
