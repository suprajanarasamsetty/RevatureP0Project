package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.Exception.SellerCreateException;
import com.Exception.SellerDeleteException;
import com.Exception.SellerNotFoundException;
import com.Exception.SellerUpdateException;
import com.dto.SellerRequest;
import com.dto.SellerResponse;

public interface SellerDAO {
	
	SellerResponse getSellerById(long id) throws SQLException, SellerNotFoundException;
	boolean CreateSeller(SellerRequest sellerRequest) throws SQLException, SellerCreateException;
	boolean UpdateSeller(SellerResponse sellerResponse) throws SQLException, SellerUpdateException;
	boolean DeleteSeller(long id) throws SQLException, SellerDeleteException;
	List<SellerResponse> getAllSellers() throws SQLException, SellerNotFoundException;

}
