package com.Exception;

public class SellerUpdateException extends Exception{
	
	public SellerUpdateException(String message) {
		 super(message);
  }

  // Constructor that accepts a message and a cause
  public SellerUpdateException(String message, Throwable cause) {
      super(message, cause);
  }


}
