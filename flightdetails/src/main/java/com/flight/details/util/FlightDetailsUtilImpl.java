package com.flight.details.util;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flight.details.entity.Flight;
import com.flight.details.exception.InvalidDataException;
import com.flight.details.external.services.FareNSeatsService;
import com.flight.details.service.DetailsService;

@Component
public class FlightDetailsUtilImpl implements FlightDetailsUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FlightDetailsUtilImpl.class);

	
	//Getting the Fare & Seats Micro-service Functions
	@Autowired
	private FareNSeatsService fareNSeatsService;
	
	//Creating the service instance
	@Autowired
	private DetailsService detailsService;
	
	@Override
	public Flight createFlight(Flight flight) {
		// TODO Auto-generated method stub
		logger.info("Creating new flight: {}", flight);
		if(flight.getNumRows()==0 || flight.getSeatsPerRow()==0) {
			 logger.error("Invalid data provided for creating flight: {}", flight);
			throw new InvalidDataException("Enter Proper Rows and Seats Data");
		}
		if(flight.getDestination()==null || flight.getSource()==null) {
			logger.error("Invalid data provided for creating flight: {}", flight);
			throw new InvalidDataException("Enter proper Source and Destination");
		}
		//Adding the new Flight in Database
		Flight flight1=detailsService.createFlight(flight);
		logger.info("New flight created: {}", flight1);
		//Creating the seats and therir fare for given flight in fares & Seat Repository
		fareNSeatsService.setSeatsNFare(flight1.getFlightId());
		logger.info("Seats and fare set for new flight");
		return flight1;
	}

	@Override
	public boolean deleteFlight(Integer id) {
		// TODO Auto-generated method stub
		logger.info("Deleting flight with ID: {}", id);
		return detailsService.deleteFlight(id);
	}

	@Override
	public Flight updateFlight(Flight flight) {
		// TODO Auto-generated method stub
		logger.info("Updating flight: {}", flight);
		return detailsService.createFlight(flight);
	}

	@Override
	public List<Flight> getFlight(String src, String desti, LocalDate departureDate) {
		// TODO Auto-generated method stub
		 logger.info("Fetching flights from {} to {} for departure date: {}", src, desti, departureDate);
		return detailsService.getFlight(src, desti, departureDate);
	}

	@Override
	public Flight getFlightById(Integer flightId) {
		// TODO Auto-generated method stub
		logger.info("Fetching flight by ID: {}", flightId);
		return detailsService.getFlightById(flightId);
	}

}
