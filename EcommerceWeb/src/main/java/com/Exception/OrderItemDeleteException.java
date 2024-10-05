package com.Exception;

public class OrderItemDeleteException extends Exception{
	
	public OrderItemDeleteException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public OrderItemDeleteException(String message, Throwable cause) {
     super(message, cause);
 }


}
