package com.Exception;

public class SellerDeleteException extends Exception{
	
	public SellerDeleteException(String message) {
		 super(message);
  }

  // Constructor that accepts a message and a cause
  public SellerDeleteException(String message, Throwable cause) {
      super(message, cause);
  }


}
