package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.Exception.OrderCreateException;
import com.Exception.OrderDeleteException;
import com.Exception.OrderNotFoundException;
import com.dto.OrdersRequest;
import com.dto.OrdersResponse;

public interface OrdersDAO {
	
	OrdersResponse getOrderById(long id) throws SQLException, OrderNotFoundException;
	boolean CreateOrder(OrdersRequest orderRequest) throws SQLException, OrderCreateException;
	boolean UpateOrder(OrdersResponse orderResponse) throws SQLException;
	boolean DeleteOrder(long id) throws SQLException, OrderDeleteException;
	List<OrdersResponse> getAllOrders() throws SQLException, OrderNotFoundException;

}
