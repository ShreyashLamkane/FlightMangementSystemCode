package com.flight.faresnseats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@Autowired
	private FaresNSeatsUtil faresNSeatsUtil;
	@PostMapping("/{flightId}")
	public ResponseEntity setSeatsNFare(@PathVariable Integer flightId ) {
		
		
		return ResponseEntity.ok(faresNSeatsUtil.createFareNSeats(flightId));
	}
}
