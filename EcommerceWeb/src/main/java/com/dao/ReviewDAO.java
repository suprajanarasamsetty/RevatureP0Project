package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.Exception.ReviewCreateException;
import com.Exception.ReviewDeleteException;
import com.Exception.ReviewNotFoundException;
import com.Exception.ReviewUpdateException;
import com.dto.ReviewRequest;
import com.dto.ReviewResponse;

public interface ReviewDAO {
	
	ReviewResponse getReviewById(long id) throws SQLException, ReviewNotFoundException;
	boolean CreateReview(ReviewRequest reviewRequest) throws ReviewCreateException;
	boolean UpdateReview(ReviewRequest reviewRequest) throws ReviewUpdateException;
	boolean DeleteReview(long id) throws ReviewDeleteException;
	List<ReviewResponse> getAllReviews() throws ReviewNotFoundException;

}
