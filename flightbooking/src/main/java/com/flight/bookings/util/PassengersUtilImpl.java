package com.flight.bookings.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.flight.bookings.entity.Passengers;
import com.flight.bookings.entity.Seat;
import com.flight.bookings.external.service.WebCheckInService;
import com.flight.bookings.service.PassengersService;

@Component
public class PassengersUtilImpl implements PassengersUtil{
	
	@Autowired
	private WebCheckInService webCheckInService;
	@Autowired
	private PassengersService passengersService;
	
	@Override
	public Passengers createPassengers(Passengers passengers) {
		// TODO Auto-generated method stub
		return passengersService.createPassengers(passengers);
	}

	@Override
	public Passengers getPassengersById(String passengerId) {
		// TODO Auto-generated method stub
		return passengersService.getPassengersById(passengerId);
	}

	@Override
	public void removePassenger(String passengerId) {
		// TODO Auto-generated method stub
		passengersService.removePassenger(passengerId);
	}

	@Override
	public Passengers updatePassenger(Passengers passenger) {
		// TODO Auto-generated method stub
		return passengersService.updatePassenger(passenger);
	}

	@Override
	public ArrayList<Passengers> getByBookingId(String bookingId) {
		// TODO Auto-generated method stub
		return passengersService.getByBookingId(bookingId);
	}

	@Override
	public void removeByBooking(String bookingId) {
		// TODO Auto-generated method stub
		passengersService.removeByBooking(bookingId);
	}

	@Override
	public void seSeatsPassenger(String bookingId) {
		// TODO Auto-generated method stub
		List<Seat> list=webCheckInService.getSeatByBookingId(bookingId);
		for(Seat seat: list) {
			Passengers p=passengersService.getPassengersById(seat.getPassengerId());
			p.setSeatNumber(seat.getSeatNumber());
			passengersService.createPassengers(p);
		}
		
	}

	@Override
	public void allocateSeat(String bookingId) {
		// TODO Auto-generated method stub
		//calling the web check in seat allocation
		webCheckInService.seatAllocation(bookingId);
		
		seSeatsPassenger(bookingId);
	}
}
