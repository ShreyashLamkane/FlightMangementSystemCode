package com.flight.faresnseats.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class FareNSeats {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String seatNumber; // Format like "26B"
	private String seatClass;
	private double fare;
	private boolean available;
	private Integer flightId; // To link the seat to a specific flight
}
