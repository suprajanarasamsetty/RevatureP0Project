package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Exception.OrderItemCreateException;
import com.Exception.OrderItemDeleteException;
import com.Exception.OrderItemNotFoundException;
import com.Exception.OrderItemUpdateException;
import com.dao.OrderItemDAOClass;
import com.dto.OrderItemRequest;
import com.dto.OrderItemResponse;

public class OrderItemService {
	
	private final OrderItemDAOClass orderItemDao;
	
	public OrderItemService() {
		orderItemDao=new OrderItemDAOClass();
	}
	
	public OrderItemResponse getOrderItemById(long id) throws SQLException, OrderItemNotFoundException {
		return orderItemDao.getOrderItemById(id);
	}
	
	public boolean CreateOrderItem(OrderItemRequest orderItemRequest) throws SQLException, OrderItemCreateException {
		return orderItemDao.CreateOrderItem(orderItemRequest);
	}
	
	public boolean UpdateOrderItem(OrderItemRequest orderItemRequest) throws SQLException, OrderItemUpdateException {
		return orderItemDao.UpdateOrderItem(orderItemRequest);
	}
	
	public boolean DeleteOrderItem(long id) throws SQLException, OrderItemDeleteException {
		return orderItemDao.DeleteOrderItem(id);
	}
	
	public List<OrderItemResponse> getAllOrderItems() throws SQLException, OrderItemNotFoundException {
		return orderItemDao.getAllOrderItems();
	}
	
	public List<OrderItemResponse> getOrderItemsByOrderId(int orderId) throws SQLException {
		return orderItemDao.getOrderItemsByOrderId(orderId);
	}

	
	public static void main(String[] args) throws SQLException, OrderItemCreateException, OrderItemNotFoundException {
		OrderItemService OrderItem=new OrderItemService();
		
		System.out.println(OrderItem.getOrderItemsByOrderId(40));
		
//		System.out.println(OrderItem.CreateOrderItem(new OrderItemRequest(1, 1, 1, 01, 7000.00)));
//		System.out.println(OrderItem.CreateOrderItem(new OrderItemRequest(2, 2, 2, 02, 4998.00)));
//		System.out.println(OrderItem.CreateOrderItem(new OrderItemRequest(3, 3, 3, 01, 66399.17)));
//		System.out.println(OrderItem.CreateOrderItem(new OrderItemRequest(4, 4, 4, 01, 91289.17)));
//		System.out.println(OrderItem.CreateOrderItem(new OrderItemRequest(5, 5, 5, 03, 11997.00)));
//		System.out.println(OrderItem.CreateOrderItem(new OrderItemRequest(6, 6, 6, 04, 5160.00)));
//		System.out.println(OrderItem.CreateOrderItem(new OrderItemRequest(7, 7, 7, 02, 6998.00)));
//		System.out.println(OrderItem.CreateOrderItem(new OrderItemRequest(8, 8, 8, 01, 1499.00)));
		//System.out.println(OrderItem.CreateOrderItem(new OrderItemRequest(9, 2, 0, 0, 0)));
		
//		List<OrderItemResponse> OrderList=OrderItem.getAllOrderItems();
//		
//		for(OrderItemResponse Orders : OrderList) {
//			System.out.println(Orders.toString());
//		}
		
	}



}
