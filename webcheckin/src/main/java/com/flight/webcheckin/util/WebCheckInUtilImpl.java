package com.flight.webcheckin.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flight.webcheckin.entity.FareNSeats;
import com.flight.webcheckin.external.service.FareNSeatsService;
@Component
public class WebCheckInUtilImpl implements WebCheckInUtil{
	
	@Autowired
	private FareNSeatsService fareNSeatsService;
	@Override
	public List<FareNSeats> getAvailableSeats(Integer flightId, String seatClass) {
		// TODO Auto-generated method stub
		return fareNSeatsService.getAvailableSeats(flightId, seatClass);
	}

}
