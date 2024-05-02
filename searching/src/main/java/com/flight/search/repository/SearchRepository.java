package com.flight.search.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.search.entity.Flight;

public interface SearchRepository extends JpaRepository<Flight, Integer> {
	List<Flight> findBySourceAndDestinationAndDepartureDate(String source, String destination, LocalDate departureDate);
}
