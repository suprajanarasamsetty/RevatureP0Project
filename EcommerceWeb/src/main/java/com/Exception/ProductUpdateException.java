package com.Exception;

public class ProductUpdateException extends Exception{

	public ProductUpdateException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public ProductUpdateException(String message, Throwable cause) {
     super(message, cause);
 }

}
