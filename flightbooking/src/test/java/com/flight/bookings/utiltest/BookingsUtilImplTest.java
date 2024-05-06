package com.flight.bookings.utiltest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.flight.bookings.entity.Bookings;
import com.flight.bookings.entity.Flight;
import com.flight.bookings.entity.Passengers;
import com.flight.bookings.external.service.FareNSeatsService;
import com.flight.bookings.external.service.FlightService;
import com.flight.bookings.service.BookingsService;
import com.flight.bookings.service.PassengersService;
import com.flight.bookings.util.BookingsUtilImpl;

@SpringBootTest(classes= {BookingsUtilImplTest.class})
public class BookingsUtilImplTest {
	 @Mock
	 private FareNSeatsService fareNSeatsService;

	 @Mock
	 private BookingsService bookingsService;

	  @Mock
	  private PassengersService passengersService;

	  @Mock
	  private FlightService flightService;

	  @InjectMocks
	  private BookingsUtilImpl bookingsUtil;

	  @BeforeEach
	   public void setup() {
	      MockitoAnnotations.initMocks(this);
	  }

	  @Test
	  public void testCreateBookings() {
	        // Mocking data
		  Bookings bookings = new Bookings();
	      bookings.setBookingId("6637c27ef9caae237f2682c9");
	      bookings.setFlightId(8);
	      bookings.setSeatClass("Economy Class");
	      bookings.setNoOfPassengers(2);

	      Flight flight = new Flight();
	      flight.setFlightId(8);

	      Passengers passenger1 = new Passengers();
	        passenger1.setPassengerName("Passenger 1");

	        Passengers passenger2 = new Passengers();
	        passenger2.setPassengerName("Passenger 2");

	        ArrayList<Passengers> passengersList = new ArrayList<>();
	        passengersList.add(passenger1);
	        passengersList.add(passenger2);

	        // Stubbing service methods
	        when(bookingsService.createBookings(bookings)).thenReturn(bookings);
	        when(passengersService.createPassengers(any(Passengers.class))).thenReturn(passenger1); // Assuming passenger creation returns the created passenger

	        when(flightService.getFlightById(bookings.getFlightId())).thenReturn(flight);

	        when(fareNSeatsService.getFareByFlightIdAndClass(bookings.getFlightId(), bookings.getSeatClass())).thenReturn(100);

	        
	        Bookings createdBookings = bookingsUtil.createBookings(bookings);

	       
	        assertNotNull(createdBookings);
	        assertEquals(bookings, createdBookings);
	        assertEquals(200, createdBookings.getTotalFare()); // Assuming fare for 2 passengers is 100 each
	    }
	    
	    @Test
	    public void testRemoveById() {
	        // Mocking data
	        String bookingId = "6637c27ef9caae237f2682c9";

	        // Calling util method
	        bookingsUtil.removeById(bookingId);

	        
	        verify(passengersService, times(1)).removeByBooking(bookingId);
	        verify(bookingsService, times(1)).removeById(bookingId);
	    }
	    
	    @Test
	    public void testGetById() {
	        // Mocking data
	        String bookingId = "6637c27ef9caae237f2682c9";
	        Bookings bookings = new Bookings();
	        bookings.setBookingId(bookingId);

	        Passengers passenger1 = new Passengers();
	        passenger1.setPassengerName("Passenger 1");

	        Passengers passenger2 = new Passengers();
	        passenger2.setPassengerName("Passenger 2");

	        ArrayList<Passengers> passengersList = new ArrayList<>();
	        passengersList.add(passenger1);
	        passengersList.add(passenger2);

	        // Stubbing service methods
	        when(passengersService.getByBookingId(bookingId)).thenReturn(passengersList);
	        when(bookingsService.getById(bookingId)).thenReturn(bookings);

	  
	        Bookings retrievedBookings = bookingsUtil.getById(bookingId);

	    
	        assertNotNull(retrievedBookings);
	        assertEquals(bookingId, retrievedBookings.getBookingId());
	        assertEquals(passengersList, retrievedBookings.getPassenger());
	    }
}
