package com.Exception;

public class WishlistUpdateException extends Exception{
	
	public WishlistUpdateException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public WishlistUpdateException(String message, Throwable cause) {
     super(message, cause);
 }


}
