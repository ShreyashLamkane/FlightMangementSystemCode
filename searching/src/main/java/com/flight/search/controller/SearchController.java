package com.flight.search.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.search.entity.Flight;
import com.flight.search.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@GetMapping
	public List<Flight> searchFlights(
            @RequestParam String src,
            @RequestParam String dest,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate){
		
		return searchService.getFlight(src, dest, departureDate);
		
	}
	
	@GetMapping("/{flightId}")
	public Flight searchByFlightId(@PathVariable Integer flightId) {
		return searchService.getFlightById(flightId);
	}
}
