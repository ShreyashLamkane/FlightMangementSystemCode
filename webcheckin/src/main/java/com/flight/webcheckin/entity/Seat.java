package com.flight.webcheckin.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("Seats")
public class Seat {
	
	@Id
	private String seatId;
	private Integer flightId;
	private String bookingId;
	private String passengerId;
	private String seatNumber; // Format like "26B"
    private String seatClass;
    
   // private FareNSeats fareNSeats;
	
}
