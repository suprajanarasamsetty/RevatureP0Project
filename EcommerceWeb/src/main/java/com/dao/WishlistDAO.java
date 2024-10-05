package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.Exception.WishlistCreateException;
import com.Exception.WishlistDeleteException;
import com.Exception.WishlistNotFoundException;
import com.Exception.WishlistUpdateException;
import com.dto.WishlistRequest;
import com.dto.WishlistResponse;

public interface WishlistDAO {
	WishlistResponse getWishlistById(long id) throws SQLException, WishlistNotFoundException;
	boolean CreateWishlist(WishlistRequest wishlistRequest) throws WishlistCreateException;
	boolean UpdateWishlist(WishlistRequest wishlistRequest) throws WishlistUpdateException;
	boolean DeleteWishlistById(long id) throws WishlistDeleteException;
	List<WishlistResponse> getAllWishlistById();

}
