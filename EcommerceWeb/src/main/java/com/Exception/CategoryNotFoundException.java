package com.Exception;

public class CategoryNotFoundException extends Exception{
	
	public CategoryNotFoundException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public CategoryNotFoundException(String message, Throwable cause) {
     super(message, cause);
 }


}
