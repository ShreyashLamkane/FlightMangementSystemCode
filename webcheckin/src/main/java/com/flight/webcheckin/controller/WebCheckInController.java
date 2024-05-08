package com.flight.webcheckin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.webcheckin.entity.FareNSeats;
import com.flight.webcheckin.entity.Seat;
import com.flight.webcheckin.util.WebCheckInUtil;

@RestController
@RequestMapping("/web")
public class WebCheckInController {
	
	 private static final Logger logger = LoggerFactory.getLogger(WebCheckInController.class);
	
	@Autowired
	private WebCheckInUtil webCheckInUtil;
	
	@GetMapping("/getAvailable/{flightId}")
	public List<FareNSeats> getSeats(@PathVariable Integer flightId,@RequestParam String seatClass) {
		logger.info("Fetching available seats for flight ID {} and seat class {}", flightId, seatClass);
		return webCheckInUtil.getAvailableSeats(flightId, seatClass);
		
	}
	
	@PostMapping("/checkIn/{bookingId}")
	public ResponseEntity<?> seatAllocation(@PathVariable String bookingId) {
		
		logger.info("Performing seat allocation for booking ID: {}", bookingId);
		webCheckInUtil.seatAllocation(bookingId);
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getSeats/{bookingId}")
	public List<Seat> getSeatByBookingId(@PathVariable String bookingId){
		logger.info("Fetching seats for booking ID: {}", bookingId);
		return webCheckInUtil.getSeatByBookingId(bookingId);
	}
	

}
	