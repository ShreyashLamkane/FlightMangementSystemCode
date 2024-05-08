package com.flight.details.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.flight.details.entity.Flight;
import com.flight.details.service.DetailsService;
import com.flight.details.util.FlightDetailsUtil;

@RestController
@RequestMapping("/details")
public class DetailsController {
	
	private static final Logger logger = LoggerFactory.getLogger(DetailsController.class);
	
	@Autowired
	private FlightDetailsUtil flightDetailsUtil;
	
	@Autowired
	private DetailsService detailsService;
	
	//Creating the new flight
	
	@PostMapping("/create")
	public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
		System.out.println("Generated ID: " + flight.getFlightId());
		logger.info("Creating new flight: {}", flight);
		return ResponseEntity.ok(flightDetailsUtil.createFlight(flight));
		//return detailsService.createFlight(flight);
	}
	
	//Getting all the available flights by source destination and date given 
	@GetMapping("/getFlight")
	public List<Flight> searchFlights(
            @RequestParam String src,
            @RequestParam String dest,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate){
		 logger.info("Searching flights from {} to {} for departure date: {}", src, dest, departureDate);
		return flightDetailsUtil.getFlight(src, dest, departureDate);
		
	}
	
	//Deleting the flights by it's ID
	
	@DeleteMapping("/deleteFlight/{id}")
	public ResponseEntity deleteById(@PathVariable Integer id) {
		logger.info("Deleting flight with ID: {}", id);
		return ResponseEntity.ok(flightDetailsUtil.deleteFlight(id));
	}
	
	//Updating the flight if there is any change
	
	@PutMapping("/update")
	public ResponseEntity<Flight> updateFlight(@RequestBody Flight flight){
		logger.info("Updating flight with ID: {}", flight.getFlightId());
		return ResponseEntity.ok(flightDetailsUtil.createFlight(flight));
	}
	
	
	//Getting the single Flight by Id
	@GetMapping("/getFlight/{flightId}")
	public Flight getFlightById(@PathVariable Integer flightId) {
		logger.info("Fetching flight by ID: {}", flightId);
		return flightDetailsUtil.getFlightById(flightId);
	}
	

}
