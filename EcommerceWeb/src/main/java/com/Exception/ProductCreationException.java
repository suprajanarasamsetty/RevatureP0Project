package com.Exception;

public class ProductCreationException extends Exception{

	public ProductCreationException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public ProductCreationException(String message, Throwable cause) {
     super(message, cause);
 }


}
