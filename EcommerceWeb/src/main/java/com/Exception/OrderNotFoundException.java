package com.Exception;

public class OrderNotFoundException extends Exception{
	
	public OrderNotFoundException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public OrderNotFoundException(String message, Throwable cause) {
     super(message, cause);
 }


}
