package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Exception.SellerCreateException;
import com.Exception.SellerDeleteException;
import com.Exception.SellerNotFoundException;
import com.Exception.SellerUpdateException;
import com.dto.SellerRequest;
import com.dto.SellerResponse;
import com.utils.ConnectionFactory;

public class SellerDAOClass implements SellerDAO{
	private static final Logger logger=LoggerFactory.getLogger(SellerDAOClass.class);

	@Override
	public SellerResponse getSellerById(long id) throws SQLException, SellerNotFoundException {
		logger.debug("Entering into getSellerById method with ID: {}",id);
		String query="SELECT *FROM Seller WHERE SellerID=?";
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setLong(1, id);
			
			try(ResultSet rs=stmt.executeQuery()){
				if(rs.next()) { 
					logger.info("Retrieving Seller with ID: {}",id);
					SellerResponse sellerResponse=new SellerResponse(
							rs.getInt("SellerID"),
							rs.getString("SellerName"),
							rs.getString("SellerEmail"),
							rs.getString("SellerPassword"),
							rs.getString("BusinessName"),
							rs.getString("BusinessDetails"),
							rs.getInt("user_id"));
					logger.info("Seller Retrieved successfully with ID: {}",id);
					
					return sellerResponse;
							
				}
				else {
					logger.warn("Seller Not Found with ID: {}", id);
					throw new SellerNotFoundException("Seller Not Found with ID: "+id);
				}
			}
			catch(SQLException e) {
				logger.error("SQL Error occurred while fetching Sellers with ID: {}",id);
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean CreateSeller(SellerRequest sellerRequest) throws SQLException, SellerCreateException {
		logger.debug("Entering into CreateSeller method");
		String query="INSERT INTO Seller(SellerName, SellerEmail, SellerPassword, BusinessName, BusinessDetails, user_id) values(?,?,?,?,?,?)";
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			logger.info("Inserting Seller");
			stmt.setString(1, sellerRequest.getSellerName());
			stmt.setString(2,sellerRequest.getSellerEmail());
			stmt.setString(3, sellerRequest.getSellerPassword());
			stmt.setString(4, sellerRequest.getBusinessName());
			stmt.setString(5, sellerRequest.getBusinessDetails());
			stmt.setInt(6, sellerRequest.getUser_id());
			
			int result=stmt.executeUpdate();
			
			if(result>0) {
				logger.info("Seller inserted successfully");
				return true;
			}
			else {
				logger.warn("Seller Not inserted");
				throw new SellerCreateException("Seller not inserted");
			}
			
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred while inserting Seller");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateSeller(SellerResponse sellerResponse) throws SQLException, SellerUpdateException {
		logger.debug("Entering into UpdateSeller method with ID: {}", sellerResponse.getSellerID());
		String query="UPDATE Seller SET SellerName=?, SellerEmail=?, SellerPassword=?, BusinessName=?, BusinessDetails=?, user_id=? WHERE SellerID=?";
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			logger.info("Updating Seller...");
			stmt.setString(1, sellerResponse.getSellerName());
			stmt.setString(2, sellerResponse.getSellerEmail());
			stmt.setString(3, sellerResponse.getSellerPassword());
			stmt.setString(4, sellerResponse.getBusinessName());
			stmt.setString(5, sellerResponse.getBusinessDetails());
			stmt.setInt(6, sellerResponse.getSellerID());
			
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Seller Updated with ID: {}",sellerResponse.getSellerID());
				return true;
			}
			else {
				logger.info("Seller Not found with ID: {}", sellerResponse.getSellerID());
				throw new SellerUpdateException("Seller Not Updated with ID: "+sellerResponse.getSellerID());
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error while Updating Seller with ID: {}", sellerResponse.getSellerID());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteSeller(long id) throws SQLException, SellerDeleteException {
		logger.debug("Entering into DeleteSeller method with ID: {}",id);
		String query="DELETE FROM Seller WHERE SellerID=?";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			logger.info("Deleting Seller..");
			stmt.setLong(1, id);
			
			int result=stmt.executeUpdate();
			
			if(result>0) {
				logger.info("Seller Deleted with ID: {}"+id);
				return true;
			}
			else {
				logger.warn("Seller Not Deleted with ID: {}",id);
				throw new SellerDeleteException("Seller Not Deleted with ID: "+id);
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred while Deleting Seller with ID: {}", id);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<SellerResponse> getAllSellers() throws SQLException, SellerNotFoundException {
		logger.debug("Entering into getAllSellers method");
		List<SellerResponse> sellerResponse=new ArrayList<SellerResponse>();
		
		String query="SELECT *FROM Seller";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			ResultSet rs=stmt.executeQuery();
			logger.info("Retrieving all Sellers");
			while(rs.next()) {
				int SellerID =rs.getInt("SellerID");
				String SellerName=rs.getString("SellerName");
				String SellerEmail=rs.getString("SellerEmail");
				String SellerPassword=rs.getString("SellerPassword");
				String BusinessName=rs.getString("BusinessName");
				String BusinessDetails=rs.getString("BusinessDetails");
				int user_id=rs.getInt("user_id");
				
				SellerResponse pr=new SellerResponse(SellerID, SellerName, SellerEmail, SellerPassword, BusinessName, BusinessDetails, user_id);
				sellerResponse.add(pr);
			}
			logger.info("Sellers Retrieved {} Successfully", sellerResponse.size());
			return sellerResponse;
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred while fetching Sellers");
			e.printStackTrace();
			throw new SellerNotFoundException("Seller Not Found");
		}
	}
	public boolean doesSellerExist(int sellerID) throws SQLException {
	    String query = "SELECT COUNT(*) FROM seller WHERE SellerID = ?";
	    try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
	         PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setInt(1, sellerID);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	        return false;
	    }
	}
	
	public Integer getSellerIdByUserId(int userId) throws SQLException {
        String query = "SELECT SellerID FROM Seller WHERE user_id = ?";
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("SellerID");
            }
            return null;
        }
    }

}
