package com.flight.bookings.exception;

public class NotSufficientSeats extends RuntimeException {
	private String msg;

	public NotSufficientSeats(String msg) {
		super(msg);
		
	}

	public NotSufficientSeats() {
		super();
	}
	
	
}
