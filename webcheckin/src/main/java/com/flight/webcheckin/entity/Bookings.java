package com.flight.webcheckin.entity;


import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class Bookings {
	
	
	private String bookingId;
	private Integer flightId;
	
	private String bookedByName;
	private Long phoneNumber;
	private String email;
	private Integer noOfPassengers;
	
	
	private ArrayList<Passengers> passenger= new ArrayList<Passengers>();;
	
	private String seatClass;
	private int totalFare;
	private Flight flight;
	
	
	
	
}
