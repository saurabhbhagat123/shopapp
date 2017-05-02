package com.shop.exception;

public class NoShopsAvailableException extends RuntimeException {
	

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NoShopsAvailableException(String message) {
		super(message);
		this.message = message;
	}
	
	
	
	

}
