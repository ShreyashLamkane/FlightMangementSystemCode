package com.flight.details.exception;

public class InvalidDataException extends RuntimeException {
	private String msg;

	public InvalidDataException(String msg) {
		super(msg);
		
	}

	public InvalidDataException() {
		super();
	}
	
	
	
}
