package com.Exception;

public class OrderItemNotFoundException extends Exception{
	
	public OrderItemNotFoundException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public OrderItemNotFoundException(String message, Throwable cause) {
     super(message, cause);
 }


}
