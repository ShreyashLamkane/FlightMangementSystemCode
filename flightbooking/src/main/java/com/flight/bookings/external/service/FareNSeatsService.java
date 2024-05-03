package com.flight.bookings.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="SEATNFARE-SERVICE")
public interface FareNSeatsService {
	@GetMapping("/seats/fare/{flightId}")
	public Integer getFareByFlightIdAndClass(@PathVariable Integer flightId, @RequestParam String seatClass);
	
}
