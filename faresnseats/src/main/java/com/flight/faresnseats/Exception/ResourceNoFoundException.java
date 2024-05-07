package com.flight.faresnseats.Exception;

public class ResourceNoFoundException extends RuntimeException {

	public ResourceNoFoundException() {
		super("Resource Not Found on server");
	}
	public ResourceNoFoundException(String msg) {
		super(msg);
	}
	
}
