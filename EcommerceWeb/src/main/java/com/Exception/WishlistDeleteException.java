package com.Exception;

public class WishlistDeleteException extends Exception{
	
	public WishlistDeleteException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public WishlistDeleteException(String message, Throwable cause) {
     super(message, cause);
 }


}
