package com.Exception;

public class SellerCreateException extends Exception{
	
	public SellerCreateException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public SellerCreateException(String message, Throwable cause) {
     super(message, cause);
 }


}
