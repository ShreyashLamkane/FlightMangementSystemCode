package com.flight.bookings.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.flight.bookings.entity.Flight;



@FeignClient(name="FLIGHT-SERVICE")
public interface FlightService {
	@GetMapping("/details/getFlight/{flightId}")
	public Flight getFlightById(@PathVariable Integer flightId);
}
