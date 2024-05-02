package com.flight.details.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class SeatsAndFares {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer seatId;
//	private Integer flightId;
//	private String passengerId;
//	private String seatNo;
//	private String seatType;
//	private String seatStatus;
//	private Integer fare;
	private String seatNumber; // Format like "26B"
    private String seatClass;
    private double fare;
    private boolean available;
    private Integer flightId; //
}
