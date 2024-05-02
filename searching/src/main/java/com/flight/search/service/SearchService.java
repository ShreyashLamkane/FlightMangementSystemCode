package com.flight.search.service;

import java.time.LocalDate;
import java.util.List;

import com.flight.search.entity.Flight;

public interface SearchService {
	List<Flight> getFlight(String src,String desti,LocalDate departureDate);
	Flight getFlightById(Integer flightId);
}
