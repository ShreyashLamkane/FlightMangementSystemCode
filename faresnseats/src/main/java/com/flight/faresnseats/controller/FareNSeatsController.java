package com.flight.faresnseats.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seats")
public class FareNSeatsController {
	
	@PostMapping
	public ResponseEntity setSeatsNFare(@RequestParam Integer flightId ) {
		
		
	}
}
