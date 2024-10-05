package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Exception.ProductCreationException;
import com.Exception.ProductDeletionException;
import com.Exception.ProductNotFoundException;
import com.Exception.ProductUpdateException;
import com.dto.ProductRequest;
import com.dto.ProductResponse;
import com.utils.ConnectionFactory;

public class ProductDAOClass implements ProductDAO{
	
	private static final Logger logger=LoggerFactory.getLogger(ProductDAOClass.class);
	@Override
	public ProductResponse getProductById(long id) throws SQLException, ProductNotFoundException {
		logger.debug("Entering into getProductById method with ID: {}",id);

		String query="SELECT *FROM Product WHERE ProductID=?";
		
		logger.info("Fetching Product ID:{}",id);
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
		PreparedStatement stmt=con.prepareStatement(query);
		
		stmt.setLong(1, id);
		
		logger.debug("Product Retrieved");
		
		try(ResultSet rs=stmt.executeQuery()){
			if(rs.next()) {
				ProductResponse productResponse=new ProductResponse(
						rs.getInt("ProductID"),
						rs.getString("ProductName"),
						rs.getDouble("ProductPrice"),
						rs.getInt("SellerID"),
						rs.getInt("CategoryID"),
						rs.getString("ProductImage"),
						rs.getString("ProductDescription"),
						rs.getInt("user_id"));
				
				return productResponse;
			}
			else {
				throw new ProductNotFoundException("Product Not Found with ID: "+id);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		    logger.error("Database Error: ", e);
		    //throw e;
		}
		return null;
	}
	}

	@Override
	public boolean CreateProduct(ProductRequest productRequest) throws SQLException, ProductCreationException {
		String query="INSERT INTO Product(ProductName, ProductPrice, SellerID, CategoryId, ProductImage, ProductDescription) VALUES(?,?,?,?,?,?)";
		
		logger.info("Adding Product:{}");
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setString(1, productRequest.getProductName());
			stmt.setDouble(2, productRequest.getProductPrice());
			stmt.setInt(3, productRequest.getSellerID());
			stmt.setInt(4, productRequest.getCategoryID());
			stmt.setString(5, productRequest.getProductImage());
			stmt.setString(6, productRequest.getProductDescription());
			
			logger.info("Product Added Successfully");
			
			int result=stmt.executeUpdate();
			con.close();
			if(result>0) {
				return true;
			}
			else {
				throw new ProductCreationException("Product Not Inserted");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		    logger.error("Database Error: ", e);
		}
		
		return false;
	}

	@Override
	public boolean UpdateProduct(ProductResponse productResponse) throws SQLException, ProductUpdateException {
		
		String query="UPDATE Product SET ProductName=?, ProductPrice=?, SellerID=?, CategoryId=?, ProductImage=?, ProductDescription=? WHERE ProductID=?";
		
		logger.info("Updating the Product with ID{}:{}",productResponse.getProductID());
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setString(1, productResponse.getProductName());
			stmt.setDouble(2, productResponse.getProductPrice());
			stmt.setInt(3, productResponse.getSellerID());
			stmt.setInt(4, productResponse.getCategoryID());
			stmt.setInt(5, productResponse.getProductID());
			stmt.setString(5, productResponse.getProductImage());
			stmt.setString(6, productResponse.getProductDescription());
			
			logger.trace("Product Updated Successfully");
			
			int result=stmt.executeUpdate();
			
			if(result>0) {
				return true;
			}
			else throw new ProductUpdateException("Product Not Updated with ID: "+productResponse.getProductID());

		}
		catch(SQLException e) {
			e.printStackTrace();
		    logger.error("Database Error: ", e);
		}
		return false;
	}

	@Override
	public boolean DeleteProductById(long id) throws SQLException, ProductDeletionException {
		
		String query="DELETE FROM Product WHERE ProductID=?";
		
		logger.info("Deleting the Product with ID : {}",id);
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setLong(1, id);
			logger.info("Product Deleted");
			
			int result=stmt.executeUpdate();
			System.out.println("Rows Affected "+result);
			
			if(result>0) {
				return true;
			}
			else throw new ProductDeletionException("Product Not Deleted with ID: "+id);
		}
		catch(SQLException e) {
			e.printStackTrace();
			logger.error("Error while deleting the Product with ID:{}",id,e);
		}
		
		return false;
	}

	@Override
	public List<ProductResponse> getAllProducts() throws ProductNotFoundException {
		
		String query="SELECT *FROM Product";
		logger.info("Fetching the all products");
		
		List<ProductResponse> productResponse=new ArrayList<>();
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()) {
			PreparedStatement stmt= con.prepareStatement(query);
			ResultSet rs=stmt.executeQuery();
			
			logger.trace("Products Retrieved");
			while(rs.next()) {
				int ProductID = rs.getInt("ProductID");
				String ProductName = rs.getString("ProductName");
				double ProductPrice = rs.getDouble("ProductPrice");
				int SellerID=rs.getInt("SellerID");
				int CategoryID = rs.getInt("CategoryID");
				String ProductImage=rs.getString("ProductImage");
				String ProductDescription=rs.getString("ProductDescription");
				int userId=rs.getInt("user_id");
								
				ProductResponse product = new ProductResponse(ProductID, ProductName, ProductPrice, SellerID, CategoryID, ProductImage, ProductDescription, userId);
				
				productResponse.add(product);
				
			}
			return productResponse;
				
		}
		catch(SQLException e) {
			e.printStackTrace();
			logger.error("Error while fetching with Products",e);
			//throw new RuntimeException("Database Error",e);
			throw new ProductNotFoundException("Product Not Found");
		}
	}
	public List<ProductResponse> getProductsBySellerId(int sellerId) throws SQLException, ProductNotFoundException {
        List<ProductResponse> products = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE SellerID = ?";

        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, sellerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	int ProductID =rs.getInt("ProductID");
				String ProductName=rs.getString("ProductName");
				double ProductPrice=rs.getDouble("ProductPrice");
				int CategoryId=rs.getInt("CategoryId");
				String ProductImage=rs.getString("ProductImage");
				String ProductDescription=rs.getString("ProductDescription");
				int userId=rs.getInt("user_id");
				
				ProductResponse pr=new ProductResponse(ProductID, ProductName, ProductPrice, sellerId, CategoryId, ProductImage, ProductDescription, userId);
				products.add(pr);
			}
			logger.info("Products Retrieved {} Successfully", products.size());
			return products;
        }
        catch(SQLException e) {
			logger.error("SQL Error occurred while fetching Products");
			e.printStackTrace();
			throw new ProductNotFoundException("Product Not Found");
		}
    }
	// Method to get product details for a list of product IDs
    public List<ProductResponse> getProductsByIds(List<Integer> productIds) throws SQLException {
        List<ProductResponse> products = new ArrayList<>();
        String placeholders = String.join(",", Collections.nCopies(productIds.size(), "?"));
        String query = "SELECT * FROM Product WHERE ProductID IN (" + placeholders + ")"; // Use appropriate syntax for handling list
        
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
             PreparedStatement stmt = conn.prepareStatement(query);
          // Set each product ID as a parameter
             for (int i = 0; i < productIds.size(); i++) {
                 stmt.setInt(i + 1, productIds.get(i));
             }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	ProductResponse productResponse=new ProductResponse(
						rs.getInt("ProductID"),
						rs.getString("ProductName"),
						rs.getDouble("ProductPrice"),
						rs.getInt("SellerID"),
						rs.getInt("CategoryID"),
						rs.getString("ProductImage"),
						rs.getString("ProductDescription"),
						rs.getInt("user_id"));
            	
                products.add(productResponse);
            }
        }
        return products;
    }

}
