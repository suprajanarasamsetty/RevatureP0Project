package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Exception.WishlistCreateException;
import com.Exception.WishlistDeleteException;
import com.Exception.WishlistNotFoundException;
import com.Exception.WishlistUpdateException;
import com.dao.ProductDAOClass;
import com.dao.WishlistDAOClass;
import com.dto.ProductResponse;
import com.dto.WishlistRequest;
import com.dto.WishlistResponse;

public class WishlistService {
	
	private final WishlistDAOClass wishlistDao;
	private final ProductDAOClass productDao;
	
	
	public WishlistService() {
		this.wishlistDao=new WishlistDAOClass();
		this.productDao = new ProductDAOClass();
	}

	public WishlistResponse getWishlistById(long id) throws SQLException, WishlistNotFoundException {
		return wishlistDao.getWishlistById(id);
	}
	
	public boolean CreateWishlist(WishlistRequest wishlistRequest) throws WishlistCreateException {
		return wishlistDao.CreateWishlist(wishlistRequest);
	}
	
	public boolean UpdateWishlist(WishlistRequest wishlistRequest) throws WishlistUpdateException {
		return wishlistDao.UpdateWishlist(wishlistRequest);
	}
	
	public boolean DeleteWishlistById(long id) throws WishlistDeleteException {
		return wishlistDao.DeleteWishlistById(id);
	}
	
	public List<WishlistResponse> getAllWishlistById() {
		return wishlistDao.getAllWishlistById();
	}
	
    public void addProductToWishlist(int userId, int productId) throws SQLException {
    	 wishlistDao.addProductToWishlist(userId, productId);
    }
    
    public void removeProductFromWishlist(int userId, int productId) throws SQLException {
    	wishlistDao.removeProductFromWishlist(userId, productId);
    }
    
    public List<ProductResponse> getProductsByIds(List<Integer> productIds) throws SQLException {
    	return productDao.getProductsByIds(productIds);
    }
    
	public List<Integer> getProductIdsByUserId(int userId) throws SQLException {
        return wishlistDao.getProductIdsByUserId(userId);
    }


	public static void main(String[] args) throws SQLException {
		WishlistService ws=new WishlistService();
		
		System.out.println(ws.getProductIdsByUserId(8));
		
//		System.out.println(ws.CreateWishlist(new WishlistRequest(1, 1, 4)));
//		System.out.println(ws.CreateWishlist(new WishlistRequest(2, 2, 9)));
//		System.out.println(ws.CreateWishlist(new WishlistRequest(3, 3, 18)));
//		System.out.println(ws.CreateWishlist(new WishlistRequest(4, 4, 17)));
//		System.out.println(ws.CreateWishlist(new WishlistRequest(5, 5, 5)));
//		System.out.println(ws.CreateWishlist(new WishlistRequest(6, 6, 7)));
		
//		List<WishlistResponse> Wishlist=ws.getAllWishlistById();
//		
//		for(WishlistResponse wishlist : Wishlist) {
//			System.out.println(wishlist.toString());
//		}
	}

}
