package com.flight.bookings.repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flight.bookings.entity.Passengers;

public interface PassengersRepository extends MongoRepository<Passengers, String> {
	ArrayList<Passengers> findAllByBookingId(String bookingId);
	void deleteAllByBookingId(String bookingId);
}
