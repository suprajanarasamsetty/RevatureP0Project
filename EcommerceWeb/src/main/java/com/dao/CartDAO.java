package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.Exception.CartCreateException;
import com.Exception.CartDeleteException;
import com.Exception.CartNotFoundException;
import com.Exception.CartUpdateException;
import com.dto.CartRequest;
import com.dto.CartResponse;

public interface CartDAO {
	
	CartResponse getCartById(long id) throws SQLException, CartNotFoundException;
	boolean CreateCart(CartRequest cartRequest) throws SQLException, CartCreateException;
	boolean UpdateCart(CartRequest cartRequest) throws SQLException, CartUpdateException;
	boolean DeleteCart(long id) throws SQLException, CartDeleteException;
	List<CartResponse> getAllCart() throws SQLException;

}
