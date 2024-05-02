package com.flight.details.util;

import java.time.LocalDate;
import java.util.List;

import com.flight.details.entity.Flight;

public interface FlightDetailsUtil {
	Flight createFlight(Flight flight);
	boolean deleteFlight(Integer id);
	Flight updateFlight(Flight flight);
	List<Flight> getFlight(String src, String desti, LocalDate departureDate);
	Flight getFlightById(Integer flightId) ;
}
