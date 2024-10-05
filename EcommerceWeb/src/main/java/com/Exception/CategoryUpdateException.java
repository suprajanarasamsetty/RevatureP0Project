package com.Exception;

public class CategoryUpdateException extends Exception{
	
	public CategoryUpdateException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public CategoryUpdateException(String message, Throwable cause) {
     super(message, cause);
 }


}
