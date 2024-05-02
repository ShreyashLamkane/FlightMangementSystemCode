package com.flight.bookings.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.bookings.entity.Bookings;
import com.flight.bookings.entity.Passengers;
import com.flight.bookings.repository.BookingsRepository;
import com.flight.bookings.service.BookingsService;
import com.flight.bookings.service.PassengersService;

@RestController
@RequestMapping("/bookings")
public class BookingsController {
	
	@Autowired
	private BookingsService bookingsService;
	
	@Autowired
	private PassengersService passengersService;
	
	
	@PostMapping
	public ResponseEntity<Bookings> createBooking(@RequestBody Bookings bookings){
		
		Bookings r=bookingsService.createBookings(bookings);
		
		for(Passengers p: bookings.getPassenger()) {
			Passengers passenger=new Passengers();
			passenger.setBookingId(r.getBookingId());
			
			passenger.setFlightId(r.getFlightId());
			passenger.setPassengerName(p.getPassengerName());
			passenger.setPassengerPhone(p.getPassengerPhone());
			passenger.setPassengerEmail(p.getPassengerEmail());
			
			passengersService.createPassengers(passenger);
		}
		return ResponseEntity.ok(r);
	}
	
	@DeleteMapping("/delete/{bookingId}")
	public void deleteBooking(@PathVariable String bookingId) {
		passengersService.removeByBooking(bookingId);
		bookingsService.removeById(bookingId);
		
	}
	
	@GetMapping("/byBookingId/{bookingId}")
	public Bookings getById(@PathVariable String bookingId) {
		
		ArrayList<Passengers> list=new ArrayList<Passengers>();
		list=passengersService.getByBookingId(bookingId);
		Bookings booked=bookingsService.getById(bookingId);
		booked.setPassenger(list);
		return booked;
	}
	
	
	
	
}
