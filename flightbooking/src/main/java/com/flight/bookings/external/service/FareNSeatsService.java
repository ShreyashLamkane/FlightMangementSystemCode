package com.flight.bookings.external.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.flight.bookings.entity.FareNSeats;


@FeignClient(name="SEATNFARE-SERVICE")
public interface FareNSeatsService {
	@GetMapping("/seats/fare/{flightId}")
	public Integer getFareByFlightIdAndClass(@PathVariable Integer flightId, @RequestParam String seatClass);
	
	
	@GetMapping("/seats/getAvailable/{flightId}")
	public List<FareNSeats> getAvailableSeats(@PathVariable Integer flightId, @RequestParam String seatClass );
}
