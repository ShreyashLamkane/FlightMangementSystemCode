package com.flight.bookings.utiltest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.flight.bookings.entity.Passengers;
import com.flight.bookings.entity.Seat;
import com.flight.bookings.external.service.WebCheckInService;
import com.flight.bookings.service.PassengersService;
import com.flight.bookings.util.PassengersUtilImpl;



@SpringBootTest(classes= {PassengersUtilImplTest.class})
public class PassengersUtilImplTest {
	   @Mock
	    private WebCheckInService webCheckInService;

	    @Mock
	    private PassengersService passengersService;

	    @InjectMocks
	    private PassengersUtilImpl passengersUtil;

	    @BeforeEach
	    public void setup() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    public void testCreatePassengers() {
	        // Mocking data
	        Passengers passengers = new Passengers();

	        
	        when(passengersService.createPassengers(passengers)).thenReturn(passengers);

	     
	        Passengers createdPassengers = passengersUtil.createPassengers(passengers);

	
	        assertNotNull(createdPassengers);
	        assertEquals(passengers, createdPassengers);
	    }

	    @Test
	    public void testGetPassengersById() {
	       
	        String passengerId = "6634c98955e327057edccab5";
	        Passengers passengers = new Passengers();
	        passengers.setPassengerId(passengerId);

	     
	        when(passengersService.getPassengersById(passengerId)).thenReturn(passengers);

	      
	        Passengers retrievedPassengers = passengersUtil.getPassengersById(passengerId);

	        
	        assertNotNull(retrievedPassengers);
	        assertEquals(passengers, retrievedPassengers);
	    }

	    @Test
	    public void testRemovePassenger() {
	        
	        String passengerId = "6634c98955e327057edccab5";

	       
	        passengersUtil.removePassenger(passengerId);


	        verify(passengersService, times(1)).removePassenger(passengerId);
	    }
	    
	    
	    @Test
	    public void testUpdatePassenger() {
	      
	        Passengers passenger = new Passengers();
	        
	      
	        when(passengersService.updatePassenger(passenger)).thenReturn(passenger);
	        
	        Passengers result = passengersUtil.updatePassenger(passenger);

	       
	        verify(passengersService).updatePassenger(passenger);

	        
	        assertEquals(passenger, result);
	    }

//	    @Test
//	    public void testGetByBookingId() {
//	        
//	        ArrayList<Passengers> passengerList = new ArrayList<>();
//	        when(passengersService.getByBookingId(anyString())).thenReturn(passengerList);
//
//	      
//	        ArrayList<Passengers> result = yourService.getByBookingId("bookingId");
//
//	       
//	        verify(passengersService).getByBookingId("bookingId");
//
//	       
//	        assertEquals(passengerList, result);
//	    }

	    @Test
	    public void testRemoveByBooking() {
	       
	    	passengersUtil.removeByBooking("bookingId");

	     
	        verify(passengersService).removeByBooking("bookingId");
	    }

	    @Test
	    public void testAllocateSeat() {
	   
	        String bookingId = "6634c98855e327057edccab2";
	        when(webCheckInService.seatAllocation(bookingId)).thenReturn(null);
	     
	        List<Seat> seatList = new ArrayList<>();
	        when(webCheckInService.getSeatByBookingId(bookingId)).thenReturn(seatList);

	  
	        Passengers passenger = new Passengers();
	        when(passengersService.getPassengersById(anyString())).thenReturn(passenger);

	      
	        passengersUtil.allocateSeat(bookingId);

	     
	        verify(webCheckInService).seatAllocation(bookingId);
	      //  verify(passengersUtil).seSeatsPassenger(bookingId);
	    }
}
