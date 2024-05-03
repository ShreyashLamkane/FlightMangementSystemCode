package com.flight.webcheckin.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.flight.webcheckin.entity.Seat;

public interface SeatService {
	Seat addSeat(Seat seat);
	List<Seat> getSeatByBookingId(String bookingId);
	
}
