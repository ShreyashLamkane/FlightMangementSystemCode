package com.flight.faresnseats.service;

import java.util.List;

import com.flight.faresnseats.entity.FareNSeats;

public interface FaresNSeatsService {
	FareNSeats setFareNSeats(FareNSeats fareNSeats);
	List<FareNSeats> setAllFareNSeats(List<FareNSeats> list);
	FareNSeats getFareByFlightIdAndClass(Integer flightId, String seatClass);
	List<FareNSeats> getAvailableSeats(Integer flightId, String seatClass);
	void changeAvailability(Integer flightId,String seatNo);
}
