package com.flight.bookings.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.flight.bookings.util.BookingsUtil;
import com.flight.bookings.util.PassengersUtil;

@RestController
@RequestMapping("/bookings")
public class BookingsController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookingsController.class);

	
	@Autowired
	private BookingsUtil bookingsUtil;

	@Autowired
	private PassengersUtil passengersUtil;

	@PostMapping
	public ResponseEntity<?> createBooking(@RequestBody Bookings bookings) {
		logger.info("Creating booking: {}", bookings);
		Bookings r = bookingsUtil.createBookings(bookings);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{bookingId}")
	public ResponseEntity<?> deleteBooking(@PathVariable String bookingId) {
		
		logger.info("Deleting booking with ID: {}", bookingId);
		passengersUtil.removeByBooking(bookingId);
		bookingsUtil.removeById(bookingId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/byBookingId/{bookingId}")
	public Bookings getBookingById(@PathVariable String bookingId) {
		
		logger.info("Fetching booking by ID: {}", bookingId);
		Bookings booked = bookingsUtil.getById(bookingId);
		return booked;
	}

}
