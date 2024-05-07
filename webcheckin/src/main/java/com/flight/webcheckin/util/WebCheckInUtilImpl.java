package com.flight.webcheckin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flight.webcheckin.entity.Bookings;
import com.flight.webcheckin.entity.FareNSeats;
import com.flight.webcheckin.entity.Passengers;
import com.flight.webcheckin.entity.Seat;
import com.flight.webcheckin.exception.ResourceNoFoundException;
import com.flight.webcheckin.external.service.BookingService;
import com.flight.webcheckin.external.service.FareNSeatsService;
import com.flight.webcheckin.external.service.PassengersService;
import com.flight.webcheckin.service.SeatService;
@Component
public class WebCheckInUtilImpl implements WebCheckInUtil{
	
	private static final Logger logger = LoggerFactory.getLogger(WebCheckInUtilImpl.class);
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private BookingService bookingService;
	
//	@Autowired
//	private PassengersService passengersService;
	
	@Autowired
	private FareNSeatsService fareNSeatsService;
	@Override
	public List<FareNSeats> getAvailableSeats(Integer flightId, String seatClass) {
		// TODO Auto-generated method stub
		logger.info("Fetching available seats for flight ID {} and seat class {}", flightId, seatClass);

		return fareNSeatsService.getAvailableSeats(flightId, seatClass);
	}
	@Override
	public boolean seatAllocation(String bookingId) {
		// TODO Auto-generated method stub
		 
		 logger.info("Performing seat allocation for booking ID: {}", bookingId);
		Bookings booking=bookingService.getBookingById(bookingId);
		if (booking == null || booking.getNoOfPassengers() == null) {
	       
	        throw new ResourceNoFoundException("Booking is Null or number of passenger is null");
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
				p1.setSeatNumber(seat.getSeatNumber());
				
				Seat seatReturned=seatService.addSeat(seat);
				bookingService.updatePassenger(p1);
				fareNSeatsService.changeAvailability(seatReturned.getFlightId(), seatReturned.getSeatNumber());
			}
			else {
				logger.warn("No available seats for booking ID: {}", bookingId);
			}
		}
		return true;
	}
	@Override
	public List<Seat> getSeatByBookingId(String bookingId) {
		// TODO Auto-generated method stub
		logger.info("Fetching seats for booking ID: {}", bookingId);
		return seatService.getSeatByBookingId(bookingId);
	}
//	@Override
//	public boolean changeToAvailable(String passengerId) {
//		// TODO Auto-generated method stub
//		Passengers passenger=bookingService.getPassenger(passengerId);
//		FareNSeats seat=fareNSeatsService.
//		return false;
//	}

}
