package com.shop.exception;

public class LatLongMissingException extends RuntimeException {

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LatLongMissingException(String message) {
		super(message);
		this.message = message;
	}
	
	
	
	
	
}
