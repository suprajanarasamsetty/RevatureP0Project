package com.Exception;

public class OrderItemUpdateException extends Exception{
	
	public OrderItemUpdateException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public OrderItemUpdateException(String message, Throwable cause) {
     super(message, cause);
 }


}
