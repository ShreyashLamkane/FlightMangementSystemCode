package com.flight.details.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.details.entity.Flight;
import com.flight.details.repository.DetailsRepository;

@Service
public class DetailsServiceImpl implements DetailsService {
	
	@Autowired
	private DetailsRepository detailsRepository;
	
	@Override
	public boolean deleteFlight(Integer id) {
		// TODO Auto-generated method stub
		detailsRepository.deleteById(id);
		return true;
	}

	@Override
	public Flight updateFlight(Flight flight) {
		// TODO Auto-generated method stub
		return detailsRepository.save(flight);
	}

	@Override
	public List<Flight> getFlight(String src, String desti, LocalDate departureDate) {
		// TODO Auto-generated method stub
		return detailsRepository.findBySourceAndDestinationAndDepartureDate(src, desti, departureDate);
	}

	@Override
	public Flight createFlight(Flight flight) {
		// TODO Auto-generated method stub
		
		return detailsRepository.save(flight);
	}
	
	public Flight getFlightById(Integer flightId) {
		
		return detailsRepository.findById(flightId).get();
	}

}
