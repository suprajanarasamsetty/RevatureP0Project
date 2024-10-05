package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Exception.CategoryCreateException;
import com.Exception.CategoryDeleteException;
import com.Exception.CategoryNotFoundException;
import com.Exception.CategoryUpdateException;
import com.dto.CategoryRequest;
import com.dto.CategoryResponse;
import com.utils.ConnectionFactory;

public class CategoryDAOClass implements CategoryDAO{
	
	private final Logger logger=LoggerFactory.getLogger(CategoryDAOClass.class);

	@Override
	public CategoryResponse getCategoryById(long id) throws SQLException, CategoryNotFoundException {
		logger.debug("Entering into getCategoryById method with ID: {}",id);
		String query="SELECT * FROM Category WHERE CategoryId=?";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setLong(1, id);
			
			try(ResultSet rs=stmt.executeQuery()){
				if(rs.next()) {
					CategoryResponse categoryResponse=new CategoryResponse(
							rs.getInt("CategoryId"),
							rs.getString("CategoryName"),
							rs.getString("imageUrl"));
					
					logger.info("Category Retrieved Successfully",id);
					
					return categoryResponse;
				}
				else {
					logger.warn("Category not found with ID: {}",id);
					throw new CategoryNotFoundException("Category Not Found with ID: "+id);
				}
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred with ID: {}",id);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean CreateCategory(CategoryRequest categoryRequest) throws SQLException, CategoryCreateException {
		logger.debug("Entering into CreateCategory method with ID: {}", categoryRequest.getCategoryId());
		String query="INSERT INTO Category(CategoryId, CategoryName) values(?,?)";
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setInt(1, categoryRequest.getCategoryId());
			stmt.setString(2, categoryRequest.getCategoryName());
			
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Category inserted successfully with ID: {}", categoryRequest.getCategoryId());
				return true;
			}
			else {
				logger.warn("Category not inserted with ID: {}",categoryRequest.getCategoryId());
				throw new CategoryCreateException("Category Not Found with ID: "+categoryRequest.getCategoryId());
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred with ID: {}",categoryRequest.getCategoryId());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateCategory(CategoryRequest categoryRequest) throws SQLException, CategoryUpdateException{
		logger.debug("Entering into UpdateCategory method with ID: {}",categoryRequest.getCategoryId());
		
		String query="UPDATE Category SET CategoryName =?, imageUrl=? WHERE CategoryId=?";
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setString(1, categoryRequest.getCategoryName());
			stmt.setString(2, categoryRequest.getImageUrl());
			stmt.setInt(3, categoryRequest.getCategoryId());
			
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Category updated successfully with ID: {}",categoryRequest.getCategoryId());
				return true;
			}
			else {
				logger.warn("Category not updated with ID: {}",categoryRequest.getCategoryId());
				throw new CategoryUpdateException("Category Update Exception"+categoryRequest.getCategoryId());
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred with ID: {}",categoryRequest.getCategoryId());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteCategoryById(long id) throws SQLException, CategoryDeleteException {
		logger.debug("Entering into DeleteCategory method with ID: {}",id);
		String query="DELETE FROM Category WHERE CategoryId=?";
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setLong(1, id);
			int result=stmt.executeUpdate();
			System.out.println("Rows Affected "+result);
			if(result>0) {
				logger.info("Category Deleted successfully with ID: {}",id);
				return true;
			}
			else {
				logger.warn("Category Not Deleted with ID: {}",id);
				throw new CategoryDeleteException("Category Delete Exception"+id);
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred with ID: {}",id);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<CategoryResponse> getAllCategories() throws SQLException, CategoryNotFoundException {
		logger.debug("Entering into getAllCategories method");
		
		List<CategoryResponse> categoryResponse=new ArrayList<>();
		
		String query = "SELECT * FROM Category";
	    try (Connection con = ConnectionFactory.getConnectionFactory().getConnection()) {
	        PreparedStatement stmt = con.prepareStatement(query);
	        
	        try (ResultSet rs = stmt.executeQuery()) {
	        	logger.info("Fetching Category..");
	            while (rs.next()) {
	                int CategoryId = rs.getInt("CategoryId");
	                String CategoryName = rs.getString("CategoryName");
	                String imageUrl=rs.getString("imageUrl");
	                
	                CategoryResponse category = new CategoryResponse(CategoryId, CategoryName, imageUrl);
	                
	                categoryResponse.add(category);
	            }
	            
	            if (categoryResponse.isEmpty()) {
	            	logger.warn("Category is Empty");
	                throw new CategoryNotFoundException("No categories found");
	            }
				logger.info("Retrieved {} Category", categoryResponse.size());
	            return categoryResponse;
	        }
	    } catch (SQLException e) {
	    	logger.error("SQL Error occurred while Retrieving all Category");
	        e.printStackTrace();
	        throw new CategoryNotFoundException("Category Not Found");
	    }
	}
}