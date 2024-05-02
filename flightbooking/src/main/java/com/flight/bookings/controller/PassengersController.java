package com.flight.bookings.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.bookings.entity.Passengers;
import com.flight.bookings.service.PassengersService;
import com.flight.bookings.util.PassengersUtil;

@RestController
@RequestMapping("/passengers")
public class PassengersController {
	
	@Autowired
	private PassengersUtil passengersUtil;
	
//	@Autowired
//	private PassengersService passengersService;
	
	@PostMapping
	public Passengers createPassenger(@RequestBody Passengers passengers) {
		
		return passengersUtil.createPassengers(passengers);
	}
	
	@GetMapping("/{passengerId}")
	public Passengers getPassenger(@PathVariable String passengerId) {
		
		return passengersUtil.getPassengersById(passengerId);
	}
	@DeleteMapping("/remove/{passengerId}")
	public void removePassenger(@PathVariable String passengerId) {
		passengersUtil.removePassenger(passengerId);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Passengers> updatePassenger(@RequestBody Passengers passengers) {
		
		return ResponseEntity.ok(passengersUtil.updatePassenger(passengers));
	}
	
	@GetMapping("/bookingId/{bookingId}")
	public ArrayList<Passengers> getByBookingId(@PathVariable String bookingId ){
		
		return passengersUtil.getByBookingId(bookingId);
	}
}
