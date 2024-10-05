package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Exception.SellerCreateException;
import com.Exception.SellerDeleteException;
import com.Exception.SellerNotFoundException;
import com.Exception.SellerUpdateException;
import com.dao.SellerDAOClass;
import com.dto.SellerRequest;
import com.dto.SellerResponse;

public class SellerService {
	
	private final SellerDAOClass sellerDao;
	
		public SellerService() {
			sellerDao =new SellerDAOClass();
		}
		
		public SellerResponse getSellerById(long id) throws SQLException, SellerNotFoundException {
			return sellerDao.getSellerById(id);
		}

		public boolean CreateSeller(SellerRequest sellerRequest) throws SQLException, SellerCreateException {
			return sellerDao.CreateSeller(sellerRequest);
		}
		
		public boolean UpdateSeller(SellerResponse sellerResponse) throws SQLException, SellerUpdateException {
			return sellerDao.UpdateSeller(sellerResponse);
		}
		
		public boolean DeleteSeller(long id) throws SQLException, SellerDeleteException {
			return sellerDao.DeleteSeller(id);
		}

		public List<SellerResponse> getAllSellers() throws SQLException, SellerNotFoundException {
			return sellerDao.getAllSellers();
		}
		public boolean doesSellerExist(int sellerID) throws SQLException {
			return sellerDao.doesSellerExist(sellerID);
		}
		
		public Integer getSellerIdByUserId(int userId) throws SQLException {
			return sellerDao.getSellerIdByUserId(userId);
		}

		 
		public static void main(String[] args) throws SQLException, SellerNotFoundException, SellerCreateException {
			SellerService ss=new SellerService();
			
			//for fetching the Seller getSellerById
			//System.out.println(ss.getSellerById(8));
			
			
			//for updating the Seller
			//System.out.println(ss.UpdateSeller(new SellerRequest(1, "Kiran", "kiran@gmail.com", "Kiran@457", "TrendHive", "hub of the latest products and trends")));
			//System.out.println(ss.UpdateSeller(new SellerRequest(2, "Surya", "surya@gmail.com", "surya@123", "AuraCommerce", "Suggests a stylish, well-curated selection of products")));
			
			//for deleting the Seller
			
			
			
			//for inserting values in Seller table
			//System.out.println(ss.CreateSeller(new SellerRequest("babulal", "babyulal@dlkfj.com", "jhjdfhfj", "kshdfk", null)));
			//System.out.println(ss.CreateSeller(new SellerRequest(1, "kiran", "Kiran@gmail.com", "kiran@123", "TrendHive", "hub of the latest products and trends")));
			//System.out.println(ss.CreateSeller(new SellerRequest(2, "Surya", "surya@gmail.com", "surya@123", "LuxeMart", "Suggests a premium shopping experience")));
			//System.out.println(ss.CreateSeller(new SellerRequest(3, "John", "john747@gmail.com", "john5245", "PurelyGoods", "Conveys quality and a focus on essential products")));
			//System.out.println(ss.CreateSeller(new SellerRequest(4, "Kane", "kane2244@gmail.com", "kane@542", "UrbanNest", "Ideal for home goods, lifestyle products, or modern apparel")));
			//System.out.println(ss.CreateSeller(new SellerRequest(5, "Mukesh", "mukesh@gmail.com", "Mukesh@123", "Trendora", "Blends “trend” and “adora,” implying trendy, desirable products")));
			//System.out.println(ss.CreateSeller(new SellerRequest(6, "Ajay Mehta", "ajay.mehta@crossword.in", "Mehta@448", "Crossword Bookstores", "A leading retail chain offering a wide range of books, magazines, and stationery across India")));
			//System.out.println(ss.CreateSeller(new SellerRequest(7, "Priya Sharma", "priya.sharma@landmark.in", "Priya@Sharma", "Landmark Bookstores", "Known for its extensive collection of books")));
			
			//for fetching values in Seller table
			List<SellerResponse> sr=ss.getAllSellers();
				for(SellerResponse Sellers:sr) {
					System.out.println(Sellers.toString());
				}
			}
		
	}
