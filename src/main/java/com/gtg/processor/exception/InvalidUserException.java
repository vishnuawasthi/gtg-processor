package com.gtg.processor.exception;

public class InvalidUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public InvalidUserException(String message) {
		this.message  = message;
	}

	public String getMessage() {
		return message;
	}
}
