package com.flight.webcheckin.external.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.flight.webcheckin.entity.FareNSeats;



@FeignClient(name="SEATNFARE-SERVICE")
public interface FareNSeatsService {
	@GetMapping("/seats/getAvailable/{flightId}")
	public List<FareNSeats> getAvailableSeats(@PathVariable Integer flightId, @RequestParam String seatClass );
}
