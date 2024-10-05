package com.Exception;

public class ReviewCreateException extends Exception{
	
	public ReviewCreateException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public ReviewCreateException(String message, Throwable cause) {
     super(message, cause);
 }


}
