package com.flight.webcheckin.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flight.webcheckin.entity.Seat;

public interface WebCheckInRepository extends MongoRepository<Seat, String> {
	
	List<Seat> findAllByBookingId(String bookingId);
}
