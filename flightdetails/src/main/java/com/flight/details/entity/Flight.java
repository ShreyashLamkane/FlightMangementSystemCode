package com.flight.details.entity;


import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="flight")
public class Flight {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
