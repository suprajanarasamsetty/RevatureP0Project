package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Exception.OrderItemCreateException;
import com.Exception.OrderItemDeleteException;
import com.Exception.OrderItemNotFoundException;
import com.Exception.OrderItemUpdateException;
import com.dto.OrderItemRequest;
import com.dto.OrderItemResponse;
import com.utils.ConnectionFactory;

public class OrderItemDAOClass implements OrderItemDAO{
	private final Logger logger=LoggerFactory.getLogger(OrderItemDAOClass.class);

	@Override
	public OrderItemResponse getOrderItemById(long id) throws SQLException, OrderItemNotFoundException {
		logger.debug("Entering into getOrderItemById method with ID: {}",id);
		
		String query="SELECT *FROM OrderItem WHERE OrderItemID=?";
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			
			PreparedStatement stmt=con.prepareStatement(query);
			
			try(ResultSet rs=stmt.executeQuery()){
				if(rs.next()) {
					OrderItemResponse orderItemResponse=new OrderItemResponse(
							rs.getInt("OrderItemID"),
							rs.getInt("Order_id"),
							rs.getInt("ProductID"),
							rs.getInt("Quantity"),
							rs.getDouble("Price"));
					
					logger.info("OrderItem Retrieved Successfully with ID: {}",id);
					
					return orderItemResponse;
				}
				else {
					logger.warn("OrderItem Not Found with ID: {}",id);
					throw new OrderItemNotFoundException("OrderItem Not Found with ID: "+id);
				}
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred while fetching OrderItems with ID: {}",id);
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean CreateOrderItem(OrderItemRequest orderItemRequest) throws SQLException, OrderItemCreateException {
		logger.debug("Entering into CreateOrderItem method with ID: {}",orderItemRequest.getOrderItemID());
		String query="INSERT INTO OrderItem(OrderItemID, Order_id, ProductID, Quantity, Price) VALUES(?,?,?,?,?)";
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setInt(1, orderItemRequest.getOrderItemID());
			stmt.setInt(2,orderItemRequest.getOrder_id());
			stmt.setInt(3, orderItemRequest.getProductID());
			stmt.setInt(4, orderItemRequest.getQuantity());
			stmt.setDouble(5, orderItemRequest.getPrice());
			
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("OrderItem Created Succesfully with ID: {}", orderItemRequest.getOrderItemID());
				return true;
			}
			else {
				logger.warn("OrderItem Not Created with ID: {}",orderItemRequest.getOrderItemID());
				throw new OrderItemCreateException("Order Item Not Inserted"+orderItemRequest.getOrderItemID());
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error while creating OrderItem with ID: {}",orderItemRequest.getOrder_id());
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean UpdateOrderItem(OrderItemRequest orderItemRequest) throws SQLException, OrderItemUpdateException {
		logger.debug("Entering into UpdateOrderItem method with ID: {}",orderItemRequest.getOrderItemID());
		String query="UPDATE OrderItem SET Order_id=?, ProductID=?, Quantity=?, Price=? WHERE OrderItemID=?";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setInt(1, orderItemRequest.getOrder_id());
			stmt.setInt(2, orderItemRequest.getProductID());
			stmt.setInt(3,orderItemRequest.getQuantity());
			stmt.setDouble(4,orderItemRequest.getPrice());
			stmt.setInt(5, orderItemRequest.getOrderItemID());
			
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Order Item Updated successfully with ID: {}",orderItemRequest.getOrderItemID());
				return true;
			}
			else {
				logger.warn("Order Item Not Updated with ID: {}",orderItemRequest.getOrderItemID());
				throw new OrderItemUpdateException("OrderItem Update Exception "+orderItemRequest.getOrderItemID());
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred while updating OrderItem with ID: {}",orderItemRequest.getOrderItemID());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteOrderItem(long id) throws SQLException, OrderItemDeleteException {
		logger.debug("Entering into DeleteOrderItem method with ID: {}",id);
		String query="DELETE FROM OrderItem WHERE OrderItemID=?";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setLong(1, id);
			
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Order Item Deleted successfully with ID: {}",id);
				return true;
			}
			else {
				logger.warn("Order Item Not Deleted with ID: {}",id);
				throw new OrderItemDeleteException("OrderITem Delete Exception "+id);
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error occurred while Deleting OrderItem with ID: {}",id);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<OrderItemResponse> getAllOrderItems() throws SQLException, OrderItemNotFoundException {
		logger.debug("Entering into getAllOrderItems method");
		List<OrderItemResponse> orderItemResponse=new ArrayList<>();
		
		String query="SELECT *FROM OrderItem";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			try(ResultSet rs=stmt.executeQuery()){
				while(rs.next()) {
					logger.info("Feteching OrderITtem...");
					int OrderItemID=rs.getInt("OrderItemID");
					int Order_id=rs.getInt("Order_id");
					int ProductID=rs.getInt("ProductID");
					int Quantity=rs.getInt("Quantity");
					Double Price=rs.getDouble("Price");
					
					OrderItemResponse OrderItem=new OrderItemResponse(OrderItemID, Order_id, ProductID, Quantity, Price);
					
					orderItemResponse.add(OrderItem);
				}
				logger.info("Retrived {} successfully",orderItemResponse.size());
				return orderItemResponse;
				
			}
			catch(SQLException e) {
				logger.error("SQL Error while fetching all OrderItems");
				e.printStackTrace();
				throw new OrderItemNotFoundException("OrderItem Not Found");
			}
		}
	}
	public List<OrderItemResponse> getOrderItemsByOrderId(int orderId) throws SQLException {
        List<OrderItemResponse> orderItems = new ArrayList<>();
        
        // SQL query to fetch order items based on the order ID
        String query = "SELECT * FROM OrderItem WHERE Order_id = ?";
        
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
               
               preparedStatement.setInt(1, orderId);
               
               try (ResultSet rs = preparedStatement.executeQuery()) {
                   while (rs.next()) {
                       // Create a new OrderItemResponse object for each row in the ResultSet
                       OrderItemResponse item = new OrderItemResponse(
                           rs.getInt("OrderItemID"),
                           rs.getInt("Order_id"),
                           rs.getInt("ProductID"),
                           rs.getInt("Quantity"),
                           rs.getDouble("Price")
                       );
                       // Add the item to the list
                       orderItems.add(item);
                   }
               }
           } catch (SQLException e) {
               logger.error("SQL Error occurred while fetching OrderItems with Order_id: {}", orderId, e);
               throw e; // Rethrow exception after logging
           }
           
           return orderItems;
    }
}

