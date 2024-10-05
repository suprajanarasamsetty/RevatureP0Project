package com.Exception;

public class CategoryDeleteException extends Exception{
	
	public CategoryDeleteException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public CategoryDeleteException(String message, Throwable cause) {
     super(message, cause);
 }


}
