package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Exception.CartCreateException;
import com.Exception.CartDeleteException;
import com.Exception.CartNotFoundException;
import com.Exception.CartUpdateException;
import com.dao.CartDAOClass;
import com.dto.CartRequest;
import com.dto.CartResponse;

public class CartService {
	
	private final CartDAOClass cartDao;
	
	public CartService() {
		cartDao=new CartDAOClass();
	}
	
	public CartResponse getCartById(long id) throws SQLException, CartNotFoundException {
		return cartDao.getCartById(id);
	}
	
	public boolean CreateCart(CartRequest cartRequest) throws SQLException, CartCreateException {
		return cartDao.CreateCart(cartRequest);
	}
	
	public boolean UpdateCart(CartRequest cartRequest) throws SQLException, CartUpdateException {
		return cartDao.UpdateCart(cartRequest);
	}
	
	public boolean DeleteCart(long id) throws SQLException, CartDeleteException {
		return cartDao.DeleteCart(id);
	}
	
	public List<CartResponse> getAllCart() throws SQLException {
		return cartDao.getAllCart();
	}
	
	public List<CartResponse> getCartItemsByUserId(Integer userId) throws SQLException {
		return cartDao.getCartItemsByUserId(userId);
	}

	
	public static void main(String[] args) throws SQLException, CartCreateException {
		CartService Cart=new CartService();
		
		
		//System.out.println(Cart.CreateCart(new CartRequest(1, 3, 10, 2875.00, 1, null)));
		List<CartResponse> cart=Cart.getAllCart();
		
		for(CartResponse CartItems:cart) {
			System.out.println(CartItems.toString());
		}
	}

}
