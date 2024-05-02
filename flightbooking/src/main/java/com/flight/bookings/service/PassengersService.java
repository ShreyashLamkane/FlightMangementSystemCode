package com.flight.bookings.service;

import java.util.ArrayList;

import com.flight.bookings.entity.Passengers;

public interface PassengersService {
	
	Passengers createPassengers(Passengers passengers);
	Passengers getPassengersById(String passengerId);
	void removePassenger(String passengerId);
	Passengers updatePassenger(Passengers passenger);
	ArrayList<Passengers> getByBookingId(String bookingId);
	void removeByBooking(String bookingId);
}
