package com.Exception;

public class ProductDeletionException extends Exception{

	public ProductDeletionException(String message) {
		 super(message);
 }

 // Constructor that accepts a message and a cause
 public ProductDeletionException(String message, Throwable cause) {
     super(message, cause);
 }


}
