package com.flight.faresnseats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.faresnseats.entity.FareNSeats;

public interface FareNSeatsRepository extends JpaRepository<FareNSeats, Integer> {
	FareNSeats findFirstByFlightIdAndSeatClass(Integer flightId, String seatClass);

	List<FareNSeats> findAllByFlightIdAndSeatClassAndAvailable(Integer flightId, String seatClass, boolean available);

	FareNSeats findByFlightIdAndSeatNumber(Integer flightId, String seatNumber);

}
