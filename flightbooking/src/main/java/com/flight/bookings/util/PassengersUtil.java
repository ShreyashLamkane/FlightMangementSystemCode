package com.flight.bookings.util;

import java.util.ArrayList;

import com.flight.bookings.entity.Passengers;

public interface PassengersUtil {
	Passengers createPassengers(Passengers passengers);
	Passengers getPassengersById(String passengerId);
	void removePassenger(String passengerId);
	Passengers updatePassenger(Passengers passenger);
	ArrayList<Passengers> getByBookingId(String bookingId);
	void removeByBooking(String bookingId);
}
