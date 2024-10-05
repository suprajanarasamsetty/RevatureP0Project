package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Exception.ProductCreationException;
import com.Exception.ProductDeletionException;
import com.Exception.ProductNotFoundException;
import com.Exception.ProductUpdateException;
import com.dao.ProductDAOClass;
import com.dto.ProductRequest;
import com.dto.ProductResponse;

public class ProductService {
	
	private final ProductDAOClass productDao;
	
	public ProductService() {
		
		productDao = new ProductDAOClass();
	}
	
	public ProductResponse getProductById(long id) throws SQLException {
			try {
				return productDao.getProductById(id);
			} catch (SQLException | ProductNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		
	}
	
	public boolean DeleteProductByID(long id) throws SQLException, ProductDeletionException{
		return productDao.DeleteProductById(id);
	}
	
	public boolean UpdateProduct(ProductResponse productResponse) throws SQLException, ProductUpdateException {
		return productDao.UpdateProduct(productResponse);
	}
	
	public boolean createProduct(ProductRequest productRequest) throws SQLException, ProductCreationException {
		return productDao.CreateProduct(productRequest);
	}
	public List<ProductResponse> getAllProducts() throws ProductNotFoundException {
		return productDao.getAllProducts();
	}
	
	public List<ProductResponse> getProductsBySellerId(int sellerId) throws SQLException, ProductNotFoundException {
		return productDao.getProductsBySellerId(sellerId);
	}

	public static void main(String[] args) throws SQLException, ProductDeletionException, ProductNotFoundException {
		
		ProductService p=new ProductService();
		
		System.out.println(p.getProductById(40));
		
		//for deleting the product
//		int ProductIDtoDelete=1;
//		p.productDao.DeleteProduct(ProductIDtoDelete);
//		System.out.println("Product Deleted Successfully");
		
		//for updating the Product;
		//System.out.println(p.UpdateProduct(new ProductRequest(2, "Levi's Men's Slim Fit Denim Shirt", 2499.00, 1, 1)));
		
//		//for inserting values in products table
//		System.out.println(p.createProduct(new ProductRequest(1, "Dior Sauvage Eau de Toilette", 7000.00, 5, 3)));
//		System.out.println(p.createProduct(new ProductRequest(3, "Apple iPhone 14", 66399.17, 5, 2)));
//		System.out.println(p.createProduct(new ProductRequest(4, "HP Spectre x360", 91289.17, 5, 2)));
//		System.out.println(p.createProduct(new ProductRequest(5, "Levi's 501 Original Jeans", 3999.00, 2, 1)));
//		System.out.println(p.createProduct(new ProductRequest(6, "Zara Basic T-Shirt", 1290.00, 2, 1)));
//		System.out.println(p.createProduct(new ProductRequest(7, "Tommy Hilfiger Polo Shirt", 3499.00, 1, 1)));
//		System.out.println(p.createProduct(new ProductRequest(8, "L'Oréal Paris Revitalift Serum", 1499.00, 4, 3)));
//		System.out.println(p.createProduct(new ProductRequest(9, "The Body Shop Tea Tree Oil", 695.00, 2, 3)));
//		System.out.println(p.createProduct(new ProductRequest(10, "Forest Essentials Night Cream", 2875.00, 2, 3)));
//		System.out.println(p.createProduct(new ProductRequest(11, "Godrej Interio Slimline Wardrobe", 18999.00, 4, 4)));
//		System.out.println(p.createProduct(new ProductRequest(12, "Urban Ladder Oliver Coffee Table", 6499.00, 4, 4)));
//		System.out.println(p.createProduct(new ProductRequest(13, "Sleepwell Activa Mattress", 12999.00, 4, 4)));
//		System.out.println(p.createProduct(new ProductRequest(14, "Atomic Habits by James Clear", 599.00, 6, 5)));
//		System.out.println(p.createProduct(new ProductRequest(15, "The Alchemist by Paulo Coelho", 399.00, 7, 5)));
//		System.out.println(p.createProduct(new ProductRequest(16, "1984 by George Orwell", 299.00, 6, 5)));
//		System.out.println(p.createProduct(new ProductRequest(17, "Titan Raga Analog Watch", 7995.00, 2, 1)));
//		System.out.println(p.createProduct(new ProductRequest(18, "PlayStation 5", 41499.17, 3, 2)));
//      System.out.println(p.createProduct(new ProductRequest(19, "Nike Air Jordan 4 RM",12795.00, 1, 1)));

//
		List<ProductResponse> products=p.getAllProducts();
		
		for(ProductResponse product : products) {
			System.out.println(product.toString());
		}
	}

}
