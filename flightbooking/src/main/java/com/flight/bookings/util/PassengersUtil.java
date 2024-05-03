package com.flight.bookings.util;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.flight.bookings.entity.Passengers;

public interface PassengersUtil {
	Passengers createPassengers(Passengers passengers);
	Passengers getPassengersById(String passengerId);
	void removePassenger(String passengerId);
	Passengers updatePassenger(Passengers passenger);
	ArrayList<Passengers> getByBookingId(String bookingId);
	void removeByBooking(String bookingId);
	void allocateSeat(String bookingId);
	public void seSeatsPassenger(String bookingId);
}
