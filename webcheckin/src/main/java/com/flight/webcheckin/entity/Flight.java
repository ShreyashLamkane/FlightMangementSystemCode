package com.flight.webcheckin.entity;


import java.time.LocalDate;
import java.time.LocalTime;

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
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int numRows;
    private int seatsPerRow;
	private Integer fare;
	
	
	
}
