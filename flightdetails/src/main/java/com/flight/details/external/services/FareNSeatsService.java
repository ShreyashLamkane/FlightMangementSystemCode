package com.flight.details.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="SEATNFARE-SERVICE")
public interface FareNSeatsService {
	
	@PostMapping("/seats/{flightId}")
	public ResponseEntity setSeatsNFare(@PathVariable Integer flightId );
}
