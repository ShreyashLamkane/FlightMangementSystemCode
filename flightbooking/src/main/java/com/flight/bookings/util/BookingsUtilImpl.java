package com.flight.bookings.util;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.flight.bookings.entity.Bookings;
import com.flight.bookings.entity.Flight;
import com.flight.bookings.entity.Passengers;
import com.flight.bookings.external.service.FlightService;
import com.flight.bookings.service.BookingsService;
import com.flight.bookings.service.PassengersService;

public class BookingsUtilImpl implements BookingsService {


	@Autowired
	private BookingsService bookingsService;
	
	@Autowired
	private PassengersService passengersService;
	
	@Autowired
	private FlightService flightService;
	@Override
	public Bookings createBookings(Bookings bookings) {
		// TODO Auto-generated method stub
//		Flight flight=flightService.getFlightById(bookings.getFlightId());
//		bookings.setFlight(flight);
//		bookings.setTotalFare(flight.getFare()*bookings.getNoOfPassengers());
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
		Flight flight=flightService.getFlightById(bookings.getFlightId());
		bookings.setFlight(flight);
		bookings.setTotalFare(flight.getFare()*bookings.getNoOfPassengers());
		bookingsService.createBookings(bookings);
		return r;
	}

	@Override
	public void removeById(String bookingId) {
		// TODO Auto-generated method stub
		passengersService.removeByBooking(bookingId);
		bookingsService.removeById(bookingId);
	}

	@Override
	public Bookings getById(String bookingId) {
		// TODO Auto-generated method stub
		ArrayList<Passengers> list=new ArrayList<Passengers>();
		list=passengersService.getByBookingId(bookingId);
		Bookings booked=bookingsService.getById(bookingId);
		booked.setPassenger(list);
		return booked;
	}

}
