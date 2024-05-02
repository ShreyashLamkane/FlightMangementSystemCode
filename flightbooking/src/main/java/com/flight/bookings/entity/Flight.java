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
	
	
	private String flight;
	private String source;
	private String destination;
	private LocalDate departureDate;
    private String departureTime;
	private Integer fare;
	
	
	
}
