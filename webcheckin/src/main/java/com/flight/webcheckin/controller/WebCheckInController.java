package com.flight.webcheckin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.webcheckin.entity.FareNSeats;
import com.flight.webcheckin.util.WebCheckInUtil;

@RestController
@RequestMapping("/web")
public class WebCheckInController {
	
	@Autowired
	private WebCheckInUtil webCheckInUtil;
	
	@GetMapping("/getAvailable/{flightId}")
	public List<FareNSeats> getSeats(@PathVariable Integer flightId,@RequestParam String seatClass) {
		
		return webCheckInUtil.getAvailableSeats(flightId, seatClass);
		
	}
}
