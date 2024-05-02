package com.flight.bookings.util;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.bookings.entity.Passengers;
import com.flight.bookings.service.PassengersService;

@Service
public class PassengersUtilImpl implements PassengersUtil {
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

}
