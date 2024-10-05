package com.entity;

public class Review {
	private int ReviewID;
	private int ProductID;
	private int user_id;
	private String Rating;
	private String Comment;
	public Review(int reviewID, int productID, int user_id, String rating, String comment) {
		super();
		ReviewID = reviewID;
		ProductID = productID;
		this.user_id = user_id;
		Rating = rating;
		Comment = comment;
	}
	public int getReviewID() {
		return ReviewID;
	}
	public void setReviewID(int reviewID) {
		ReviewID = reviewID;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getRating() {
		return Rating;
	}
	public void setRating(String rating) {
		Rating = rating;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	
	

}
