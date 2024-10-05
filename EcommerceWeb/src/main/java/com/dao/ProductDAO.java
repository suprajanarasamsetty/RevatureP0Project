package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.Exception.ProductCreationException;
import com.Exception.ProductDeletionException;
import com.Exception.ProductNotFoundException;
import com.Exception.ProductUpdateException;
import com.dto.ProductRequest;
import com.dto.ProductResponse;

public interface ProductDAO {
	
	ProductResponse getProductById(long id) throws SQLException, ProductNotFoundException, ProductNotFoundException;
	boolean CreateProduct(ProductRequest productRequest) throws SQLException, ProductCreationException;
	boolean UpdateProduct(ProductResponse productResponse) throws SQLException, ProductUpdateException;
	boolean DeleteProductById(long id) throws SQLException, ProductDeletionException;
	List<ProductResponse> getAllProducts() throws ProductNotFoundException;

}
