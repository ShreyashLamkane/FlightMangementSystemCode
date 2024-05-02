package com.flight.bookings.util;

import com.flight.bookings.entity.Bookings;

public interface BookingsUtil {
	Bookings createBookings(Bookings bookings) ;
	void  removeById(String bookingId);
	Bookings getById(String bookingId);
}
