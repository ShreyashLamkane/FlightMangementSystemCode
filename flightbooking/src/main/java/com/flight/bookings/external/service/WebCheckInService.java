package com.flight.bookings.external.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.flight.bookings.entity.Seat;


@FeignClient(name="WEBCHECKIN-SERVICE")
public interface WebCheckInService {
	@GetMapping("/web/getSeats/{bookingId}")
	public List<Seat> getSeatByBookingId(@PathVariable String bookingId);
	
	@PostMapping("/web/checkIn/{bookingId}")
	public ResponseEntity<?> seatAllocation(@PathVariable String bookingId);
}
