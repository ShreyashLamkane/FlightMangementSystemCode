package com.flight.faresnseats.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.faresnseats.entity.FareNSeats;
import com.flight.faresnseats.util.FaresNSeatsUtil;

@RestController
@RequestMapping("/seats")
public class FareNSeatsController {
	
	private static final Logger logger = LoggerFactory.getLogger(FareNSeatsController.class);

	
	@Autowired
	private FaresNSeatsUtil faresNSeatsUtil;
	@PostMapping("/{flightId}")
	public ResponseEntity setSeatsNFare(@PathVariable Integer flightId ) {
		
		logger.info("Setting seats and fare for flight with ID: {}", flightId);
		return ResponseEntity.ok(faresNSeatsUtil.createFareNSeats(flightId));
	}
	
	@GetMapping("/fare/{flightId}")
	public Integer getFareByFlightIdAndClass(@PathVariable Integer flightId, @RequestParam String seatClass) {
		 logger.info("Fetching fare for flight with ID: {} and seat class: {}", flightId, seatClass);
		return faresNSeatsUtil.getFareByFlightIdAndClass(flightId, seatClass);
	}
	
	@GetMapping("/getAvailable/{flightId}")
	public List<FareNSeats> getAvailableSeats(@PathVariable Integer flightId, @RequestParam String seatClass ){
		
		return faresNSeatsUtil.getAvailableSeats(flightId, seatClass);
	}
	
	@PostMapping("/change/{flightId}/{seatNumber}")
	public ResponseEntity<?> changeAvailability(@PathVariable Integer flightId, @PathVariable String seatNumber) {
		logger.info("Changing availability of seat {} for flight with ID: {}", seatNumber, flightId);
		faresNSeatsUtil.changeAvailability(flightId, seatNumber);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	

}
