package com.flight.flightsecurity.exception;

public class AuthenticationFailedException extends RuntimeException {

	private String msg;

	public AuthenticationFailedException(String msg) {
		super(msg);

	}

}
