package com.Exception;

public class UserUpdateException extends Exception{
	
	public UserUpdateException(String message) {
		 super(message);
  }

  // Constructor that accepts a message and a cause
  public UserUpdateException(String message, Throwable cause) {
      super(message, cause);
  }


}
