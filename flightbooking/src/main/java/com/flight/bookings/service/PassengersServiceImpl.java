package com.flight.bookings.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flight.bookings.entity.Passengers;
import com.flight.bookings.repository.PassengersRepository;

@Service
public class PassengersServiceImpl implements PassengersService {
	
	@Autowired
	private PassengersRepository passengersRepository;
	
	@Override
	public Passengers createPassengers(Passengers passengers) {
		// TODO Auto-generated method stub
		return passengersRepository.save(passengers);
	}
	
	public Passengers getPassengersById(String passengerId) {
		
		return passengersRepository.findById(passengerId).get();
	}
	
	public void removePassenger(String passengerId) {
		passengersRepository.deleteById(passengerId);
	}
	
	public Passengers updatePassenger(Passengers passenger) {
		
		return passengersRepository.save(passenger);
	}
	
	public ArrayList<Passengers> getByBookingId(String bookingId){
		
		return passengersRepository.findAllByBookingId(bookingId);
	}
	
	public void removeByBooking(String bookingId) {
		
		passengersRepository.deleteAllByBookingId(bookingId);
	}

	
}
