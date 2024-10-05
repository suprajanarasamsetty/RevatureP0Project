package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.Exception.OrderCreateException;
import com.Exception.OrderDeleteException;
import com.Exception.OrderNotFoundException;
import com.dto.OrdersRequest;
import com.dto.OrdersResponse;
import com.utils.ConnectionFactory;


public class OrdersDAOClass implements OrdersDAO{
	
	private static final Logger logger=LoggerFactory.getLogger(OrdersDAOClass.class);

	@Override
	public OrdersResponse getOrderById(long id) throws SQLException, OrderNotFoundException {
		logger.debug("Enter into getOrderById method with ID: {}",id);
				
		String query="SELECT *FROM Orders WHERE Order_id=?";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
		PreparedStatement stmt=con.prepareStatement(query);
		stmt.setLong(1, id);
		try(ResultSet rs=stmt.executeQuery()){
			if(rs.next()) {
				Timestamp timestamp = rs.getTimestamp("Order_Date");
				LocalDateTime orderDate = timestamp != null ? timestamp.toLocalDateTime() : null;
				OrdersResponse ordersResponse=new OrdersResponse(
						rs.getInt("Order_id"),
						rs.getInt("user_id"),
						rs.getString("ShippingAddress"),
						rs.getString("BillingAddress"),
						orderDate,
						rs.getString("Order_Status"),
						rs.getInt("ProductID"));
				
				logger.info("Order Retrieved successfully with ID: {}",id);
				
				return ordersResponse;
			}
			else {
				logger.warn("Order not found with ID: {}",id);
				throw new OrderNotFoundException("Order Not Found with ID: "+id);
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred while fetching Order with ID: {}",id);
			e.printStackTrace();
		}
		}
		
		return null;
	}

	@Override
	public boolean CreateOrder(OrdersRequest orderRequest) throws SQLException, OrderCreateException {
		logger.debug("Entering into CreateOrder method with ID: {}",orderRequest.getOrder_id());
		String query="INSERT INTO Orders(user_id, ShippingAddress, BillingAddress, Order_Date, Order_Status, ProductID) VALUES(?,?,?,?,?,?)";
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setInt(1, orderRequest.getUser_id());
			stmt.setString(2, orderRequest.getShippingAddress());
			stmt.setString(3, orderRequest.getBillingAddress());
			
            Timestamp timestamp = Timestamp.valueOf(orderRequest.getOrder_Date());
 
            stmt.setTimestamp(4, timestamp);
            
            stmt.setString(5, orderRequest.getOrder_Status());
			stmt.setInt(6, orderRequest.getProductID());

            
            int result=stmt.executeUpdate();
            if(result>0) {
            	logger.info("Order Created successfully with ID: {}",orderRequest.getOrder_id());
            	return true;
            }
            else {
            	logger.warn("Order Not Created with ID: {}", orderRequest.getOrder_id());
            	throw new OrderCreateException("Order Not Inserted with ID: "+orderRequest.getOrder_id());
            }
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred while Creating Order with ID: {}", orderRequest.getOrder_id());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpateOrder(OrdersResponse orderResponse) throws SQLException {
		logger.debug("Entering into UdpateOrder method with ID: {}",orderResponse.getOrder_id());
		String query="UPDATE Orders SET user_id=?, ShippingAddress=?, BillingAddress=?, Order_Date=?, Order_Status=?, ProductID=? WHERE Order_id=?";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setInt(1, orderResponse.getUser_id());
			stmt.setString(2, orderResponse.getShippingAddress());
			stmt.setString(3, orderResponse.getBillingAddress());
			Timestamp timestamp = Timestamp.valueOf(orderResponse.getOrder_Date());
            stmt.setTimestamp(4, timestamp);
            stmt.setString(5, orderResponse.getOrder_Status());
            stmt.setInt(6, orderResponse.getProductID());
            stmt.setInt(7, orderResponse.getOrder_id());
            
            int result=stmt.executeUpdate();
            if(result>0) {
            	logger.info("Order Updated successfully with ID: {}",orderResponse.getOrder_id());
            	return true;
            }
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred while updating order with ID: {}",orderResponse.getOrder_id());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteOrder(long id) throws SQLException, OrderDeleteException {
		logger.debug("Entering into DeleteOrder method with ID: {}",id);
		String query="DELETE FROM Orders WHERE Order_id=?";
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			
			PreparedStatement stmt=con.prepareStatement(query);
			stmt.setLong(1, id);
			
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Order Deleted successfully with ID: {}",id);
				return true;
			}
			else {
				logger.warn("Order Not deleted with ID: {}",id);
				throw new OrderDeleteException("Order Not Deleted with ID: "+id);
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred while deleting Order with ID: {}",id);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<OrdersResponse> getAllOrders() throws SQLException, OrderNotFoundException {
		logger.debug("Entering into getAllOrders method");
		String query="SELECT *FROM Orders";
		List<OrdersResponse> ordersResponse=new ArrayList<>();
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			try(ResultSet rs=stmt.executeQuery()){
				logger.info("Fetching Orders...");
				while(rs.next()) {
						int Order_id=rs.getInt("Order_id");
						int user_id=rs.getInt("user_id");
						String ShippingAddress=rs.getString("ShippingAddress");
						String BillingAddress=rs.getString("BillingAddress");
						
						Timestamp timestamp=rs.getTimestamp("Order_Date");
		                LocalDateTime orderDate = timestamp != null ? timestamp.toLocalDateTime() : null;
		                
						String Order_Status=rs.getString("Order_Status");
						int ProductID=rs.getInt("ProductID");
						
						OrdersResponse Orders =new OrdersResponse(Order_id, user_id, ShippingAddress, BillingAddress, orderDate, Order_Status, ProductID);
						ordersResponse.add(Orders);
			}
				logger.info("Orders Retrieved {} Successfully",ordersResponse.size());
				return ordersResponse;
		}
			catch(SQLException e) {
				logger.error("SQL Error occurred while fetching all Orders");
				e.printStackTrace();
				throw new OrderNotFoundException("Order Not Found Exception");
			}
		}
	}
	  public List<OrdersResponse> getOrdersByUserId(int userId) throws SQLException, OrderNotFoundException {
	        List<OrdersResponse> orderList = new ArrayList<>();
	        String query = "SELECT Order_id, ShippingAddress, BillingAddress, Order_Date, Order_Status, ProductID "
	                + "FROM Orders WHERE User_id = ?";
	        
	        try (Connection con = ConnectionFactory.getConnectionFactory().getConnection();
	             PreparedStatement stmt = con.prepareStatement(query)) {
	            
	            stmt.setInt(1, userId);
	            ResultSet rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	                OrdersResponse order = new OrdersResponse();
	                order.setOrder_id(rs.getInt("Order_id"));
	                order.setShippingAddress(rs.getString("ShippingAddress"));
	                order.setBillingAddress(rs.getString("BillingAddress"));
	                order.setOrder_Date(rs.getTimestamp("Order_Date").toLocalDateTime());
	                order.setOrder_Status(rs.getString("Order_Status"));
	                order.setProductID(rs.getInt("ProductID"));
	                
	                orderList.add(order);
	            }
	        }
	        return orderList;
	    }
	  
	  public List<OrdersResponse> getOrdersBySellerId(int sellerId) throws SQLException {
	        List<OrdersResponse> orders = new ArrayList<>();
	        String query = "SELECT o.Order_id, o.ShippingAddress, o.BillingAddress, o.Order_Date, o.Order_Status, o.ProductID, "
	                + "p.ProductName "
	                + "FROM Orders o "
	                + "JOIN Product p ON o.ProductID = p.ProductID "
	                + "JOIN Seller s ON p.SellerID = s.SellerID "
	                + "WHERE s.SellerID = ?";

	        try (Connection con = ConnectionFactory.getConnectionFactory().getConnection();
	             PreparedStatement stmt = con.prepareStatement(query)) {

	            stmt.setInt(1, sellerId);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                OrdersResponse order = new OrdersResponse();
	                order.setOrder_id(rs.getInt("Order_id"));
	                order.setShippingAddress(rs.getString("ShippingAddress"));
	                order.setBillingAddress(rs.getString("BillingAddress"));

	                java.sql.Timestamp timestamp = rs.getTimestamp("Order_Date");
	                order.setOrder_Date(timestamp != null ? timestamp.toLocalDateTime() : null);
	                
	                order.setOrder_Status(rs.getString("Order_Status"));
	                order.setProductID(rs.getInt("ProductID"));
	                
	                orders.add(order);
	            }
	        }
	        return orders;
	    }
	  
	  public Integer getOrderIdForUser(Integer userId) throws SQLException {
	        // Define your SQL query to get the most recent order for the user
	        String query = "SELECT Order_id FROM Orders WHERE user_id = ? ORDER BY Order_Date DESC LIMIT 1";
	        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
	             PreparedStatement preparedStatement = connection.prepareStatement(query);
	             
	            preparedStatement.setInt(1, userId);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    // Retrieve the OrderID
	                    return resultSet.getInt("Order_id");
	                }
	            }
	        }

	        // Return null if no order found
	        return null;
	    }

}
