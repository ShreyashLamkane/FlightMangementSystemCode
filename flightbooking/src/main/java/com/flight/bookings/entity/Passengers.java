package com.flight.bookings.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("Passengers")
public class Passengers {
	
	@Id
	private String passengerId;
	
	
	private Integer flightId;
	private String	bookingId;
	
	private String passengerName;
	private Long passengerPhone;
	private String passengerEmail;
}
