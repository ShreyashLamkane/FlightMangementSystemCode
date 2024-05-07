package com.flight.bookings.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.flight.bookings.entity.Passengers;
import com.flight.bookings.entity.Seat;
import com.flight.bookings.exception.ResourceNoFoundException;
import com.flight.bookings.external.service.WebCheckInService;
import com.flight.bookings.service.PassengersService;

@Component
public class PassengersUtilImpl implements PassengersUtil{
	
	private static final Logger logger = LoggerFactory.getLogger(PassengersUtilImpl.class);

	
	@Autowired
	private WebCheckInService webCheckInService;
	@Autowired
	private PassengersService passengersService;
	
	@Override
	public Passengers createPassengers(Passengers passengers) {
		// TODO Auto-generated method stub
		logger.info("Creating passenger: {}", passengers);
		if(passengers==null)
			throw new ResourceNoFoundException("Passenger is empty to be saved");
		return passengersService.createPassengers(passengers);
	}

	@Override
	public Passengers getPassengersById(String passengerId) {
		// TODO Auto-generated method stub
		logger.info("Fetching passenger by ID: {}", passengerId);
		Passengers passenger=passengersService.getPassengersById(passengerId);
		if(passenger==null)
			throw new ResourceNoFoundException("Passenger with given ID Not Found");
		//return passengersService.getPassengersById(passengerId);
		return passenger;
	}

	@Override
	public void removePassenger(String passengerId) {
		// TODO Auto-generated method stub
		logger.info("Removing passenger with ID: {}", passengerId);
		if(passengersService.getPassengersById(passengerId)==null)
			throw new ResourceNoFoundException("Passenger with given ID Not Found");
		passengersService.removePassenger(passengerId);
	}

	@Override
	public Passengers updatePassenger(Passengers passenger) {
		// TODO Auto-generated method stub
		logger.info("Updating passenger: {}", passenger);
		return passengersService.updatePassenger(passenger);
	}

	@Override
	public ArrayList<Passengers> getByBookingId(String bookingId) {
		// TODO Auto-generated method stub
		logger.info("Fetching passengers by booking ID: {}", bookingId);
		return passengersService.getByBookingId(bookingId);
	}

	@Override
	public void removeByBooking(String bookingId) {
		// TODO Auto-generated method stub
		logger.info("Removing passengers by booking ID: {}", bookingId);
		passengersService.removeByBooking(bookingId);
	}

	@Override
	public void seSeatsPassenger(String bookingId) {
		// TODO Auto-generated method stub
		 logger.info("Setting seats for passengers with booking ID: {}", bookingId);
		List<Seat> list=webCheckInService.getSeatByBookingId(bookingId);
		if(list.size()==0) {
			throw new ResourceNoFoundException("Seats Not Found for Booking Id");
		}
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
