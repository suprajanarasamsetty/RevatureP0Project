package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Exception.ReviewCreateException;
import com.Exception.ReviewDeleteException;
import com.Exception.ReviewNotFoundException;
import com.Exception.ReviewUpdateException;
import com.dto.ReviewRequest;
import com.dto.ReviewResponse;
import com.utils.ConnectionFactory;

public class ReviewDAOClass implements ReviewDAO{
	private static final Logger logger=LoggerFactory.getLogger(ReviewDAOClass.class);

	@Override
	public ReviewResponse getReviewById(long id) throws SQLException, ReviewNotFoundException {
		logger.debug("Entering into getReviewById method with ID: {}",id);
		String query="SELECT *FROM Review WHERE ReviewID=?";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
		PreparedStatement stmt=con.prepareStatement(query);
		
		try(ResultSet rs=stmt.executeQuery()){
			if(rs.next()) {
				logger.info("Retrieving Review with ID: {}",id);
				ReviewResponse reviewResponse=new ReviewResponse(
						rs.getInt("ReviewID"),
						rs.getInt("ProductID"),
						rs.getInt("user_id"),
						rs.getInt("Rating"),
						rs.getString("Comment"));
				logger.info("Review Retrieved Successfully with ID: {}",id);
				
				return reviewResponse;
			}
			else {
				logger.warn("Review Not Found with ID: {}",id);
				throw new ReviewNotFoundException("Review Not Found with ID: "+id);
			
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred while fetching Review with ID: {}",id);
			e.printStackTrace();
		}
		return null;
	}
	}

	@Override
	public boolean CreateReview(ReviewRequest reviewRequest) throws ReviewCreateException {
		logger.debug("Entering itno CreateReview method with ID: {}",reviewRequest.getReviewID());
		String query="INSERT INTO Review(ReviewID, ProductID, user_id, Rating, Comment) VALUES(?,?,?,?,?)";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setInt(1, reviewRequest.getReviewID());
			stmt.setInt(2, reviewRequest.getProductID());
			stmt.setInt(3, reviewRequest.getUser_id());
			stmt.setInt(4, reviewRequest.getRating());
			stmt.setString(5, reviewRequest.getComment());
			
			int result=stmt.executeUpdate();
			con.close();
			if(result>0) {
				logger.info("Review Created Successfully with ID: {}",reviewRequest.getReviewID());
				return true;
			}
			else {
				logger.warn("Review not inserted with ID: {}",reviewRequest.getReviewID());
				throw new ReviewCreateException("Review Not Inserted with ID: "+reviewRequest.getReviewID());
			
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred while creating Review with ID: {}",reviewRequest.getReviewID());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateReview(ReviewRequest reviewRequest) throws ReviewUpdateException {
		logger.debug("Entering into UpdateReview method with ID: {}", reviewRequest.getReviewID());
		
        String query="UPDATE Review SET ProductID=?, user_id=?, Rating=?, Comment=? WHERE ReviewID=?";
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			
			PreparedStatement stmt=con.prepareStatement(query);
			logger.info("Updating Review");
			
			stmt.setInt(1, reviewRequest.getProductID());
			stmt.setInt(2, reviewRequest.getUser_id());
			stmt.setInt(3, reviewRequest.getRating());
			stmt.setString(4, reviewRequest.getComment());
			stmt.setInt(5, reviewRequest.getReviewID());
			
			int result=stmt.executeUpdate();
			
			if(result>0) {
				logger.info("Review Updated successfully with ID: {}",reviewRequest.getReviewID());
				return true;
			}
			else {
				logger.warn("Review not updated with ID: {}", reviewRequest.getReviewID());
				throw new ReviewUpdateException("Review Not Updated with ID: "+reviewRequest.getReviewID());
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred while Updating Review with ID: {}", reviewRequest.getReviewID());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean DeleteReview(long id) throws ReviewDeleteException {
		logger.debug("Entering into DeleteReview with ID; {}", id);
		
		String query="DELETE FROM Review WHERE ReviewID=?";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			logger.info("Deleting Review...");
			
			stmt.setLong(1, id);
			
			int result=stmt.executeUpdate();
			System.out.println("Rows Affected "+result);
			
			if(result>0) {
				logger.info("Review Deleted succesfully with ID: {}", id);
				return true;
			}
			else {
				logger.warn("Review Not Deleted with ID: {}",id);
				throw new ReviewDeleteException("Review Not Deleted with ID: "+id);
			
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred while Deleting Review with ID: {}",id);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<ReviewResponse> getAllReviews() throws ReviewNotFoundException {
		logger.debug("Entering into getAllReviews method");
        String query="SELECT *FROM Review";
		
		List<ReviewResponse> reviewResponse=new ArrayList<>();
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()) {
			PreparedStatement stmt= con.prepareStatement(query);
			ResultSet rs=stmt.executeQuery();
			
			logger.info("Fetching all Reviews...");
			while(rs.next()) {
				int ReviewID = rs.getInt("ReviewID");
				int ProductID = rs.getInt("ProductID");
				int user_id=rs.getInt("user_id");
				int Rating = rs.getInt("Rating");
				String Comment=rs.getString("Comment");
				
				ReviewResponse Review = new ReviewResponse(ReviewID, ProductID, user_id, Rating, Comment);
				
				reviewResponse.add(Review);
				
			}
			logger.info("Reviews Retrieved {} Successfully",reviewResponse.size());
			return reviewResponse;
				
		}
		catch(SQLException e) {
			logger.error("SQL Error while fetching all Reviews");
			e.printStackTrace();
			throw new ReviewNotFoundException("Review Not Found");
		}
		
	}

	
}
