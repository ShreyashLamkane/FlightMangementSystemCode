package com.flight.bookings.exception;

public class ResourceNoFoundException extends RuntimeException {

	public ResourceNoFoundException() {
		super("Resource Not Found on server");
	}
	public ResourceNoFoundException(String msg) {
		super(msg);
	}
	
}
