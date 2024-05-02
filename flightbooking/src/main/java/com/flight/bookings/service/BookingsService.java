package com.flight.bookings.service;

import com.flight.bookings.entity.Bookings;

public interface BookingsService {
	
	Bookings createBookings(Bookings bookings) ;
	void  removeById(String bookingId);
	Bookings getById(String bookingId);
	
}
