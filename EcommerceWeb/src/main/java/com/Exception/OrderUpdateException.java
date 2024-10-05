package com.Exception;

public class OrderUpdateException extends Exception{
	
	public OrderUpdateException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public OrderUpdateException(String message, Throwable cause) {
     super(message, cause);
 }


}
