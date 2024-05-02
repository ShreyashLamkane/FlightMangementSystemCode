package com.flight.bookings.entity;


import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
	
	private Integer flightId;
	
	
	private String flightNumber;
	private String source;
	private String destination;
	private LocalDate departureDate;
    private String departureTime;
    private int numRows;
    private int seatsPerRow;
	private Integer fare;
	
	
	
}
