package com.flight.webcheckin.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.flight.webcheckin.entity.Bookings;
import com.flight.webcheckin.entity.Passengers;



@FeignClient(name="BOOKING-SERVICE")
public interface BookingService {
	@GetMapping("/bookings/byBookingId/{bookingId}")
	public Bookings getBookingById(@PathVariable String bookingId) ;
	
	@PutMapping("/passengers/update")
	public ResponseEntity<Passengers> updatePassenger(@RequestBody Passengers passengers) ;
	
	@GetMapping("/passengers/{passengerId}")
	public Passengers getPassenger(@PathVariable String passengerId);
}
