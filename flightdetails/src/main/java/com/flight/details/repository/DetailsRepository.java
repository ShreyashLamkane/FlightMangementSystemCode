package com.flight.details.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.details.entity.Flight;

public interface DetailsRepository extends JpaRepository<Flight, Integer> {
	//Getting the flight by Source ,destination and date from the database 
	List<Flight> findBySourceAndDestinationAndDepartureDate(String source, String destination, LocalDate departureDate);
	
}
