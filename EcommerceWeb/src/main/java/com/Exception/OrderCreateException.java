package com.Exception;

public class OrderCreateException extends Exception{
	
	public OrderCreateException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public OrderCreateException(String message, Throwable cause) {
     super(message, cause);
 }


}
