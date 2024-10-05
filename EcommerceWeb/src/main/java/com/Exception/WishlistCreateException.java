package com.Exception;

public class WishlistCreateException extends Exception{
	
	public WishlistCreateException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public WishlistCreateException(String message, Throwable cause) {
     super(message, cause);
 }


}
