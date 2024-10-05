package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.Exception.CartCreateException;
import com.Exception.CartDeleteException;
import com.Exception.CartNotFoundException;
import com.Exception.CartUpdateException;
import com.dto.CartRequest;
import com.dto.CartResponse;
import com.utils.ConnectionFactory;

public class CartDAOClass implements CartDAO{
	
	private static final Logger logger=LoggerFactory.getLogger(CartDAOClass.class);

	@Override
	public CartResponse getCartById(long id) throws SQLException, CartNotFoundException {
		logger.debug("Entering getCartById method with ID: {}",id);
		
		String query="SELECT * FROM Cart WHERE CartID=?";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setLong(1, id);
			
			try(ResultSet rs=stmt.executeQuery()){
				if(rs.next()) {
					CartResponse cartResponse=new CartResponse(
							rs.getInt("CartID"),
							rs.getInt("user_id"),
							rs.getInt("ProductID"),
							rs.getDouble("Price"),
							rs.getInt("Quantity"),
							rs.getString("DiscountCoupon"));
					
					logger.info("Cart Retrieved successfully: {}",id);
					
					return cartResponse;
							
				}
				else {
					logger.warn("Cart not found with ID: {}",id);
					throw new CartNotFoundException("Cart Not Found With ID: "+id);
				}
			}
			catch(SQLException e) {
				logger.error("SQL Error with ID: {}",id);
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean CreateCart(CartRequest cartRequest) throws SQLException, CartCreateException {
		logger.debug("Entering CreateCart method with request: {}",cartRequest);
		String query="INSERT INTO Cart(CartID, user_id, ProductID, TotalPrice, Quantity, DiscountCoupon) VALUES(?,?,?,?,?,?)";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setInt(1, cartRequest.getCartID());
			stmt.setInt(2, cartRequest.getUser_id());
			stmt.setInt(3, cartRequest.getProductID());
			stmt.setDouble(4,cartRequest.getTotalPrice());
			stmt.setInt(5, cartRequest.getQuantity());
			stmt.setString(6, cartRequest.getDiscountCoupon());
			
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Cart created successfully with ID: {}",cartRequest.getCartID());
				return true;
			}
			else {
				logger.warn("Failed to create cart with ID: {}",cartRequest.getCartID());
				throw new CartCreateException("Cart Not Created with ID: "+cartRequest.getCartID());
			}
			
		}
		catch(SQLException e) {
			logger.error("SQL Error with ID: {}", cartRequest.getCartID());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateCart(CartRequest cartRequest) throws SQLException, CartUpdateException {
		logger.debug("Entering UpdateCart method with request: {}",cartRequest);
		String query="UPDATE Cart SET user_id=?, ProductID=?, TotalPrice=?, Quantity=?, DiscountCoupon=? WHERE CartID=?";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setInt(1, cartRequest.getUser_id());
			stmt.setInt(2,cartRequest.getProductID());
			stmt.setDouble(3, cartRequest.getTotalPrice());
			stmt.setInt(4, cartRequest.getQuantity());
			stmt.setString(5, cartRequest.getDiscountCoupon());
			stmt.setInt(6, cartRequest.getCartID());
			
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Cart updated successfully with ID: {}",cartRequest.getCartID());
				return true;
			}
			else {
				logger.warn("Failed to update cart with ID: {}",cartRequest.getCartID());
				throw new CartUpdateException("Cart Not Updated with ID: "+cartRequest.getCartID());
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occured with ID: {}",cartRequest.getCartID());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteCart(long id) throws SQLException, CartDeleteException {
		logger.debug("Entering DeleteCart method with ID: {}",id);
		
		String query="DELECT FROM Cart WHERE CartID=?";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			stmt.setLong(1, id);
			
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("cart deleted successfully with ID: {}",id);
				return true;
			}
			else {
				logger.warn("Failed to delete cart with ID: {}",id);
				throw new CartDeleteException("Cart Not Deleted with ID: "+id);
			}
			
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred with ID: {}",id);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<CartResponse> getAllCart() throws SQLException {
		logger.debug("Entering getAllCart method");
		List<CartResponse> cartResponse=new ArrayList<>();
		String query="SELECT * FROM Cart";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			try(ResultSet rs=stmt.executeQuery()){
				logger.info("Cart Fetching...");
				while(rs.next()) {
					int CartID=rs.getInt("CartID");
					int user_id=rs.getInt("user_id");
					int ProductID=rs.getInt("ProductID");
					double TotalPrice=rs.getDouble("TotalPrice");
					int Quantity=rs.getInt("Quantity");
					String DiscountCoupon=rs.getString("DiscountCoupon");
					
					CartResponse Cart =new CartResponse(CartID, user_id, ProductID, TotalPrice, Quantity, DiscountCoupon);
					cartResponse.add(Cart);
				}
				logger.info("Retrieved {} carts", cartResponse.size());
				return cartResponse;
			}
			catch(SQLException e) {
				logger.error("SQL Error occurred while retrieving all carts");
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<CartResponse> getCartItemsByUserId(Integer userId) throws SQLException {
        List<CartResponse> cartItems = new ArrayList<>();
        String query = "SELECT * FROM Cart WHERE user_id = ?";

        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    CartResponse item = new CartResponse(
                        rs.getInt("CartID"),
                        rs.getInt("user_id"),
                        rs.getInt("ProductID"),
                        rs.getDouble("TotalPrice"),
                        rs.getInt("Quantity"),
                        rs.getString("DiscountCoupon")
                    );
                    cartItems.add(item);
                }
            }
        }

        return cartItems;
    }
}
