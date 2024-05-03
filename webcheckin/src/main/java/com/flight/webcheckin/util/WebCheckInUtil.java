package com.flight.webcheckin.util;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.flight.webcheckin.entity.FareNSeats;

public interface WebCheckInUtil {
	List<FareNSeats> getAvailableSeats(Integer flightId,String seatClass);
}
