package com.flight.details.util;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flight.details.entity.Flight;
import com.flight.details.external.services.FareNSeatsService;
import com.flight.details.service.DetailsService;

@Component
public class FlightDetailsUtilImpl implements FlightDetailsUtil {
	
	//Getting the Fare & Seats Micro-service Functions
	@Autowired
	private FareNSeatsService fareNSeatsService;
	
	//Creating the service instance
	@Autowired
	private DetailsService detailsService;
	
	@Override
	public Flight createFlight(Flight flight) {
		// TODO Auto-generated method stub
		
		//Adding the new Flight in Database
		Flight flight1=detailsService.createFlight(flight);
		
		//Creating the seats and therir fare for given flight in fares & Seat Repository
		fareNSeatsService.setSeatsNFare(flight1.getFlightId());
		return flight1;
	}

	@Override
	public boolean deleteFlight(Integer id) {
		// TODO Auto-generated method stub
		return detailsService.deleteFlight(id);
	}

	@Override
	public Flight updateFlight(Flight flight) {
		// TODO Auto-generated method stub
		return detailsService.createFlight(flight);
	}

	@Override
	public List<Flight> getFlight(String src, String desti, LocalDate departureDate) {
		// TODO Auto-generated method stub
		return detailsService.getFlight(src, desti, departureDate);
	}

	@Override
	public Flight getFlightById(Integer flightId) {
		// TODO Auto-generated method stub
		return detailsService.getFlightById(flightId);
	}

}
