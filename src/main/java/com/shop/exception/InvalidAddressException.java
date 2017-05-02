package com.shop.exception;

public class InvalidAddressException extends RuntimeException {

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InvalidAddressException(String message) {
		super(message);
		this.message = message;
	}

}
