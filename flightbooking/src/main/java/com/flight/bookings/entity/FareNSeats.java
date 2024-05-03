package com.flight.bookings.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FareNSeats {
	
		
	    private Integer id;
	    private String seatNumber; // Format like "26B"
	    private String seatClass;
	    private double fare;
	    private boolean available;
	    private Integer flightId; // To link the seat to a specific flight
}
