package com.flight.webcheckin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flight.webcheckin.entity.Bookings;
import com.flight.webcheckin.entity.FareNSeats;
import com.flight.webcheckin.entity.Passengers;
import com.flight.webcheckin.entity.Seat;
import com.flight.webcheckin.external.service.BookingService;
import com.flight.webcheckin.external.service.FareNSeatsService;
import com.flight.webcheckin.service.SeatService;
@Component
public class WebCheckInUtilImpl implements WebCheckInUtil{
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private FareNSeatsService fareNSeatsService;
	@Override
	public List<FareNSeats> getAvailableSeats(Integer flightId, String seatClass) {
		// TODO Auto-generated method stub
		return fareNSeatsService.getAvailableSeats(flightId, seatClass);
	}
	@Override
	public boolean seatAllocation(String bookingId) {
		// TODO Auto-generated method stub
		Bookings booking=bookingService.getBookingById(bookingId);
		if (booking == null || booking.getNoOfPassengers() == null) {
	       
	        return false;
	    }
		 Random random = new Random();
	        System.out.println(booking);
		//Getting all the available seats
		List<FareNSeats> seats=fareNSeatsService.getAvailableSeats(booking.getFlightId(), booking.getSeatClass());
		ArrayList<Passengers> p=booking.getPassenger();
		for(int i=0;i<booking.getNoOfPassengers();i++) {
			Seat seat=new Seat();
			seat.setBookingId(bookingId);
			seat.setFlightId(booking.getFlightId());
			Passengers p1=p.get(i);
			seat.setPassengerId(p1.getPassengerId());
			seat.setSeatClass(booking.getSeatClass());
			
			if (!seats.isEmpty()) {
				int randomIndex = random.nextInt(seats.size());
				String s=seats.get(randomIndex).getSeatNumber();
				seat.setSeatNumber(s);
				seats.remove(randomIndex);
				
				
				Seat seatReturned=seatService.addSeat(seat);
				
				fareNSeatsService.changeAvailability(seatReturned.getFlightId(), seatReturned.getSeatNumber());
			}
			else {
				
			}
		}
		return true;
	}
	@Override
	public List<Seat> getSeatByBookingId(String bookingId) {
		// TODO Auto-generated method stub
		return seatService.getSeatByBookingId(bookingId);
	}

}
