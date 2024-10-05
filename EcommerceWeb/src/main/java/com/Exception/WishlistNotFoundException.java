package com.Exception;

public class WishlistNotFoundException extends Exception{
	
	public WishlistNotFoundException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public WishlistNotFoundException(String message, Throwable cause) {
     super(message, cause);
 }


}
