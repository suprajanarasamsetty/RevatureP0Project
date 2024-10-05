package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Exception.WishlistCreateException;
import com.Exception.WishlistDeleteException;
import com.Exception.WishlistNotFoundException;
import com.Exception.WishlistUpdateException;
import com.dto.ProductResponse;
import com.dto.WishlistRequest;
import com.dto.WishlistResponse;
import com.service.ProductService;
import com.utils.ConnectionFactory;

public class WishlistDAOClass implements WishlistDAO{
	private static final Logger logger=LoggerFactory.getLogger(WishlistDAOClass.class);

	@Override
	public WishlistResponse getWishlistById(long id) throws SQLException, WishlistNotFoundException {
		logger.debug("Entering into getWishlistById method with ID: {}",id);
        String query="SELECT *FROM Wishlist WHERE WishlistID=?";
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			stmt.setLong(1, id);
			
			try(ResultSet rs=stmt.executeQuery()){
				if(rs.next()) { 
					logger.info("Fetching Wishlist with ID: {}",id);
					WishlistResponse wishlistResponse=new WishlistResponse(
							rs.getInt("WishlistID"),
							rs.getInt("user_id"),
							rs.getInt("ProductID"));
					
					logger.info("Wishlist Retrieved Successfully with ID: {}",id);
					return wishlistResponse;
							
				}
				else {
					logger.warn("Wishlist Not Retrieved with ID: {}",id);
					throw new WishlistNotFoundException("Wishlist Not Found with ID: "+id);
				
				}
			}
			catch(SQLException e) {
				logger.error("SQL Error occurred while Fetching Wishlist with ID: {}"+id);
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean CreateWishlist(WishlistRequest wishlistRequest) throws WishlistCreateException {
		logger.debug("Entering into CreateWishlist method with ID: {}",wishlistRequest.getWishlistID());
		String query="INSERT INTO Wishlist(WishlistID, user_id, ProductID) VALUES(?,?,?)";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			logger.info("Creating Wishlsit..");
			stmt.setInt(1, wishlistRequest.getWishlistID());
			stmt.setInt(2, wishlistRequest.getUser_id());
			stmt.setInt(3, wishlistRequest.getProductID());
	
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Wihslist Retrieved Successfully with ID: {}", wishlistRequest.getWishlistID());
				return true;
			}
			else {
				logger.warn("Wishlist Not found with ID: {}" ,wishlistRequest.getWishlistID());
				throw new WishlistCreateException("Wishlist not inserted with ID: "+wishlistRequest.getWishlistID());
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred with ID: {}",wishlistRequest.getWishlistID());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateWishlist(WishlistRequest wishlistRequest) throws WishlistUpdateException {
		logger.debug("Entering into UpdateWishlist method with ID: {}",wishlistRequest.getWishlistID());
        String query="UPDATE Wishlist SET ProductID=?, user_id=?, ProductID=? WHERE WishlistID=?";
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			logger.info("Updating Wishlist...");
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setInt(1, wishlistRequest.getProductID());
			stmt.setInt(2, wishlistRequest.getUser_id());
			stmt.setInt(3, wishlistRequest.getWishlistID());
			
			int result=stmt.executeUpdate();
			
			if(result>0) {
				logger.info("Wishlist Updated Successfully with ID: {}",wishlistRequest.getWishlistID());
				return true;
			}
			else {
				logger.warn("Wishlist not updated with ID: {}",wishlistRequest.getWishlistID());
				throw new WishlistUpdateException("Wishlist Not Updated with ID: "+wishlistRequest.getWishlistID());
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred with ID: {}",wishlistRequest.getWishlistID());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteWishlistById(long id) throws WishlistDeleteException {
		logger.debug("Entering into DeleteWishlistByID method with ID: {}",id);
		String query="DELETE FROM Wishlist WHERE WishlistID=?";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setLong(1, id);
			
			int result=stmt.executeUpdate();
			logger.info("Deleting Wishlist...");
			System.out.println("Rows Affected "+result);
			
			if(result>0) {
				logger.info("Wishlist Deleted Successfully with ID: {}",+id);
				return true;
			}
			else throw new WishlistDeleteException("Wishlist Not Deleted with ID: "+id);
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred with ID: {}",+id);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<WishlistResponse> getAllWishlistById() {
		logger.debug("Entering WishlistResponse method");
		String query="SELECT *FROM Wishlist";
		
		List<WishlistResponse> wishlistResponse=new ArrayList<>();
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()) {
			PreparedStatement stmt= con.prepareStatement(query);
			ResultSet rs=stmt.executeQuery();
			logger.info("Fetching Wishlist...");
			while(rs.next()) {
				int WishlistID = rs.getInt("WishlistID");
				int user_id=rs.getInt("user_id");
				int ProductID = rs.getInt("ProductID");
				
				WishlistResponse Wishlist = new WishlistResponse(WishlistID, user_id, ProductID);
				
				wishlistResponse.add(Wishlist);
				
			}
			logger.info("Wishlist Retrieved {} Successfully",wishlistResponse.size());
			return wishlistResponse;
				
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred while fetching all Wishlist items");
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<Integer> getProductIdsByUserId(int userId) throws SQLException {
        List<Integer> productIds = new ArrayList<>();
        String query = "SELECT ProductID FROM Wishlist WHERE user_id = ?";
        
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                productIds.add(rs.getInt("ProductID"));
            }
        }
        return productIds;

    }
	public void addProductToWishlist(int userId, int productId) throws SQLException {
        String query = "INSERT INTO Wishlist (user_id, ProductID) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        }
    }

    public void removeProductFromWishlist(int userId, int productId) throws SQLException {
        String query = "DELETE FROM Wishlist WHERE user_id = ? AND ProductID = ?";
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        }
    }
	
}
