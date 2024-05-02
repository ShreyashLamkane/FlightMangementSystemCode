package com.flight.search.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.search.entity.Flight;
import com.flight.search.repository.SearchRepository;

@Service
public class SearchServiceImpl implements SearchService{
	@Autowired
	private SearchRepository searchRepository;
	@Override
	public List<Flight> getFlight(String src, String desti, LocalDate departureDate) {
		// TODO Auto-generated method stub
		
		return searchRepository.findBySourceAndDestinationAndDepartureDate(src, desti, departureDate);
	}
	
	public Flight getFlightById(Integer flightId) {
		return searchRepository.findById(flightId).get();
	}

}
