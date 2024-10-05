package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Exception.ReviewCreateException;
import com.Exception.ReviewDeleteException;
import com.Exception.ReviewNotFoundException;
import com.Exception.ReviewUpdateException;
import com.dao.ReviewDAOClass;
import com.dto.ReviewRequest;
import com.dto.ReviewResponse;

public class ReviewService {
	
	private final ReviewDAOClass reviewDao;
	
	public ReviewService() {
		reviewDao=new ReviewDAOClass();
	}
	
	public ReviewResponse getReviewById(long id) throws SQLException, ReviewNotFoundException {
		return reviewDao.getReviewById(id);
	}
	
	public boolean CreateReview(ReviewRequest reviewRequest) throws ReviewCreateException {
		return reviewDao.CreateReview(reviewRequest);
	}
	
	public boolean UpdateReview(ReviewRequest reviewResquest) throws ReviewUpdateException {
		return reviewDao.UpdateReview(reviewResquest);
	}
	
	public boolean DeleteReview(long id) throws ReviewDeleteException {
		return reviewDao.DeleteReview(id);
	}
	
	public List<ReviewResponse> getAllReviews() throws ReviewNotFoundException {
		return reviewDao.getAllReviews();
	}
	
	public static void main(String[] args) throws ReviewCreateException, ReviewNotFoundException {
		ReviewService rs=new ReviewService();
		
		//System.out.println(rs.CreateReview(new ReviewRequest(1, 1, 1, 5, "Dior Sauvage is a perfect blend of freshness and spice. It's versatile, long-lasting, and always gets me compliments. A must-have fragrance!")));
		//System.out.println(rs.CreateReview(new ReviewRequest(2, 2, 6, 4, "Great fit and quality! Levi's Slim Fit Denim Shirt is stylish, comfortable, and versatile—perfect for both casual and semi-formal looks")));
		//System.out.println(rs.CreateReview(new ReviewRequest(3, 0, 0, 0, null)));
		//System.out.println(rs.CreateReview(new ReviewRequest(4, 0, 0, 0, null)));
		
		List<ReviewResponse> Review = rs.getAllReviews();
		
		for(ReviewResponse review : Review) {
			System.out.println(review.toString());
		}
	}


}
