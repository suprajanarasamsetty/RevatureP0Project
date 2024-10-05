package com.Exception;

public class SellerNotFoundException extends Exception{
	public SellerNotFoundException(String message) {
		 super(message);
  }

  // Constructor that accepts a message and a cause
  public SellerNotFoundException(String message, Throwable cause) {
      super(message, cause);
  }


}
