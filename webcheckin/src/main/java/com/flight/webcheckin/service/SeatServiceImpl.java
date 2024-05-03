package com.flight.webcheckin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.webcheckin.entity.Seat;
import com.flight.webcheckin.repository.WebCheckInRepository;

@Service
public class SeatServiceImpl implements SeatService {
	
	@Autowired
	private WebCheckInRepository webCheckInRepository;
	
	@Override
	public Seat addSeat(Seat seat) {
		// TODO Auto-generated method stub
		return webCheckInRepository.save(seat);
	}

	@Override
	public List<Seat> getSeatByBookingId(String bookingId) {
		// TODO Auto-generated method stub
		return webCheckInRepository.findAllByBookingId(bookingId);
	}

	
	

}
