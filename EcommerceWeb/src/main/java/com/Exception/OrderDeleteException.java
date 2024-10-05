package com.Exception;

public class OrderDeleteException extends Exception{
	
	public OrderDeleteException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public OrderDeleteException(String message, Throwable cause) {
     super(message, cause);
 }


}
