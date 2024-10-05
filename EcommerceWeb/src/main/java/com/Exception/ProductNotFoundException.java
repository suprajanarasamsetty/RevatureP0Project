package com.Exception;

@SuppressWarnings("serial")
public class ProductNotFoundException extends Exception{
	public ProductNotFoundException(String message) {
		 super(message);
}

// Constructor that accepts a message and a cause
public ProductNotFoundException(String message, Throwable cause) {
    super(message, cause);
}
public ProductNotFoundException(Throwable cause) {
    super(cause);
}

}
