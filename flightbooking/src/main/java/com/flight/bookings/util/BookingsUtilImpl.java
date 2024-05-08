package com.flight.bookings.util;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.flight.bookings.entity.Bookings;
import com.flight.bookings.entity.FareNSeats;
import com.flight.bookings.entity.Flight;
import com.flight.bookings.entity.Passengers;
import com.flight.bookings.entity.TransactionDetails;
import com.flight.bookings.exception.NotSufficientSeats;
import com.flight.bookings.exception.ResourceNoFoundException;
import com.flight.bookings.external.service.FareNSeatsService;
import com.flight.bookings.external.service.FlightService;
import com.flight.bookings.external.service.TransactionService;
import com.flight.bookings.service.BookingsService;
import com.flight.bookings.service.PassengersService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Component
public class BookingsUtilImpl implements BookingsUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(BookingsUtilImpl.class);
	
	private static final String KEY="rzp_test_NlKCkWX4YhN434";
	private static final String SECRET_KEY="sje5hqRa3NysNN5Sz8P1OcSt";
	private static final String CURRENCY="INR";
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private FareNSeatsService fareNSeatsService;

	@Autowired
	private BookingsService bookingsService;

	@Autowired
	private PassengersService passengersService;

	@Autowired
	private FlightService flightService;

	@Override
	public Bookings createBookings(Bookings bookings) {

		List<FareNSeats> seats = fareNSeatsService.getAvailableSeats(bookings.getFlightId(), bookings.getSeatClass());
		if (seats.size() < bookings.getNoOfPassengers()) {
			logger.error("Not enough seats available for passengers in booking: {}", bookings.getBookingId());
			throw new NotSufficientSeats("Not Enough seats For Passengers");
		}
		Bookings r = bookingsService.createBookings(bookings);
		if (r == null) {
			logger.error("Booking not done for booking: {}", bookings.getBookingId());
			throw new ResourceNoFoundException("Booking Not Done");
		}
		Flight flight = flightService.getFlightById(bookings.getFlightId());
		if(flight==null) {
			logger.error("Flight not found for booking: {}", bookings.getBookingId());
			throw new ResourceNoFoundException("Flight Not Found");
		}
		for (Passengers p : bookings.getPassenger()) {
			Passengers passenger = new Passengers();
			passenger.setBookingId(r.getBookingId());

			passenger.setFlightId(r.getFlightId());
			passenger.setPassengerName(p.getPassengerName());
			passenger.setPassengerPhone(p.getPassengerPhone());
			passenger.setPassengerEmail(p.getPassengerEmail());

			passengersService.createPassengers(passenger);
		}

//		Flight flight = flightService.getFlightById(bookings.getFlightId());
//		if(flight==null) {
//			throw new ResourceNoFoundException("Flight Not Found");
//		}
		bookings.setFlight(flight);

		Integer fare = fareNSeatsService.getFareByFlightIdAndClass(bookings.getFlightId(), bookings.getSeatClass());

		bookings.setTotalFare(fare * bookings.getNoOfPassengers());
		TransactionDetails transaction= transactionService.createTransaction(bookings.getTotalFare());
		bookings.setTransactionDetails(transaction);
		bookingsService.createBookings(bookings);

		return r;
	}

	@Override
	public void removeById(String bookingId) {
		// TODO Auto-generated method stub
		logger.info("Removing booking with ID: {}", bookingId);
		if(bookingsService.getById(bookingId)==null) {
			logger.error("Booking not found with ID: {}", bookingId);
			throw new ResourceNoFoundException("Booking not Found");
		}
		passengersService.removeByBooking(bookingId);
		bookingsService.removeById(bookingId);
	}

	@Override
	public Bookings getById(String bookingId) {
		// TODO Auto-generated method stub
		logger.info("Fetching booking by ID: {}", bookingId);
		ArrayList<Passengers> list = new ArrayList<Passengers>();
		list = passengersService.getByBookingId(bookingId);
		Bookings booked =null;
		try {
			booked = bookingsService.getById(bookingId);
		}
		catch(NoSuchElementException ex){
			logger.error("Booking not found with ID: {}", bookingId);
			throw new ResourceNoFoundException("Booking not Found");
		}
		if(list.size()==0) {
			logger.error("Passengers not found for booking with ID: {}", bookingId);
			throw new ResourceNoFoundException("Passengers not Found");
		}
//		if(booked==null) {
//			logger.error("Booking not found with ID: {}", bookingId);
//			throw new ResourceNoFoundException("Booking not Found");
//		}
		booked.setPassenger(list);
		return booked;
	}
	
	public void createTransaction(Integer amount) {
		try {
			RazorpayClient razorpayClient= new RazorpayClient(KEY, SECRET_KEY);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("amount", amount*100);
			jsonObject.put("currency", CURRENCY);
			
			Order order=razorpayClient.orders.create(jsonObject);
			System.out.println(order);
			
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	private void prepareTransactionDetails(Order order) {
		String orderId=order.get("id");
		String currency=order.get("currency");
		String amount=order.get("amount");
	}

}
