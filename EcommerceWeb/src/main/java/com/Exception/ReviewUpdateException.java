package com.Exception;

public class ReviewUpdateException extends Exception{
	
	public ReviewUpdateException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public ReviewUpdateException(String message, Throwable cause) {
     super(message, cause);
 }


}
