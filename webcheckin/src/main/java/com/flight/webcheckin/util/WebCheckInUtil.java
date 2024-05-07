package com.flight.webcheckin.util;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.flight.webcheckin.entity.FareNSeats;
import com.flight.webcheckin.entity.Seat;

public interface WebCheckInUtil {
	List<FareNSeats> getAvailableSeats(Integer flightId,String seatClass);
	boolean seatAllocation(String bookingId);
	List<Seat> getSeatByBookingId(String bookingId);
	//public boolean changeToAvailable(String passengerId);
}
