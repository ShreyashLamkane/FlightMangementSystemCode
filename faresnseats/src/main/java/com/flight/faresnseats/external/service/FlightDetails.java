package com.flight.faresnseats.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.flight.faresnseats.entity.Flight;

@FeignClient(name = "FLIGHT-SERVICE")
public interface FlightDetails {

	@GetMapping("/details/getFlight/{flightId}")
	public Flight getFlightById(@PathVariable Integer flightId);
}
