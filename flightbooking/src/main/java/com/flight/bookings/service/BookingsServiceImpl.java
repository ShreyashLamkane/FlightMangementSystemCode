package com.flight.bookings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.bookings.entity.Bookings;
import com.flight.bookings.repository.BookingsRepository;

@Service
public class BookingsServiceImpl implements BookingsService {
	
	@Autowired
	private BookingsRepository bookingRepository;
	
	@Override
	public Bookings createBookings(Bookings bookings) {
		// TODO Auto-generated method stub
		return bookingRepository.save(bookings);
	}
	
	public void  removeById(String bookingId) {
		bookingRepository.deleteById(bookingId);
	}
	
	
	public Bookings getById(String bookingId) {
		return bookingRepository.findById(bookingId).get();
	}
}
