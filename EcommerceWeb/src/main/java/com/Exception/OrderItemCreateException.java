package com.Exception;

public class OrderItemCreateException extends Exception{
	
	public OrderItemCreateException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public OrderItemCreateException(String message, Throwable cause) {
     super(message, cause);
 }


}
