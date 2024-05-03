package com.flight.faresnseats.util;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.flight.faresnseats.entity.FareNSeats;

public interface FaresNSeatsUtil {
	List<FareNSeats> createFareNSeats(Integer flightId);
	Integer getFareByFlightIdAndClass(Integer flightId,String seatClass);
	List<FareNSeats> getAvailableSeats(Integer flightId, String seatClass );
}
