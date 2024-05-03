package com.flight.webcheckin.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.flight.webcheckin.entity.Bookings;



@FeignClient(name="BOOKING-SERVICE")
public interface BookingService {
	@GetMapping("/bookings/byBookingId/{bookingId}")
	public Bookings getBookingById(@PathVariable String bookingId) ;
}
