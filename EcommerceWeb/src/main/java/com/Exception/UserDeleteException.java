package com.Exception;

public class UserDeleteException extends Exception{
	public UserDeleteException(String message) {
		 super(message);
  }

  // Constructor that accepts a message and a cause
  public UserDeleteException(String message, Throwable cause) {
      super(message, cause);
  }

}
