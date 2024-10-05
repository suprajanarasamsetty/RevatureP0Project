package com.Exception;

public class ReviewDeleteException extends Exception{
	
	public ReviewDeleteException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public ReviewDeleteException(String message, Throwable cause) {
     super(message, cause);
 }


}
