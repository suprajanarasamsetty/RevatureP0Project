package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.Exception.OrderItemCreateException;
import com.Exception.OrderItemDeleteException;
import com.Exception.OrderItemNotFoundException;
import com.Exception.OrderItemUpdateException;
import com.dto.OrderItemRequest;
import com.dto.OrderItemResponse;

public interface OrderItemDAO {
	
	OrderItemResponse getOrderItemById(long id) throws SQLException, OrderItemNotFoundException;
	boolean CreateOrderItem(OrderItemRequest orderItemRequest) throws SQLException, OrderItemCreateException;
	boolean UpdateOrderItem(OrderItemRequest orderItemRequest) throws SQLException, OrderItemUpdateException;
	boolean DeleteOrderItem(long id) throws SQLException, OrderItemDeleteException;
	List<OrderItemResponse> getAllOrderItems() throws SQLException, OrderItemNotFoundException;

}
