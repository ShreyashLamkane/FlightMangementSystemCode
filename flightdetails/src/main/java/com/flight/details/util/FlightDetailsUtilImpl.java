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
	
	@Autowired
	private FareNSeatsService fareNSeatsService;
	
	@Autowired
	private DetailsService detailsService;
	
	@Override
	public Flight createFlight(Flight flight) {
		// TODO Auto-generated method stub
		Flight flight1=detailsService.createFlight(flight);
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
