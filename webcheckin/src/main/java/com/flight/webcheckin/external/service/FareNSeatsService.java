package com.flight.webcheckin.external.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flight.webcheckin.entity.FareNSeats;



@FeignClient(name="SEATNFARE-SERVICE")
public interface FareNSeatsService {
	@GetMapping("/seats/getAvailable/{flightId}")
	public List<FareNSeats> getAvailableSeats(@PathVariable Integer flightId, @RequestParam String seatClass );
	
	@PostMapping("/seats/change/{flightId}/{seatNumber}")
	public ResponseEntity<?> changeAvailability(@PathVariable Integer flightId, @PathVariable String seatNumber) ;
}
