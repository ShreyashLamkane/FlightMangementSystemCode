package com.flight.faresnseats.utiltest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
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

import com.flight.faresnseats.entity.FareNSeats;
import com.flight.faresnseats.entity.Flight;
import com.flight.faresnseats.external.service.FlightDetails;
import com.flight.faresnseats.service.FaresNSeatsService;
import com.flight.faresnseats.util.FaresNSeatsUtilImpl;

@SpringBootTest(classes= {FareNSeatsUtilImplTest.class})
public class FareNSeatsUtilImplTest {
	 	@Mock
	    private FaresNSeatsService faresNSeatsService;

	    @Mock
	    private FlightDetails flightDetails;

	    @InjectMocks
	    private FaresNSeatsUtilImpl faresNSeatsUtil;

	    @BeforeEach
	    public void setup() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    public void testCreateFareNSeats() {
	        // Mocking flight details
	        Flight flight = new Flight();
	        flight.setFlightId(8);;
	        flight.setNumRows(10);
	        flight.setSeatsPerRow(6);
	        flight.setFare(100);
	        when(flightDetails.getFlightById(1)).thenReturn(flight);

	      
	        List<FareNSeats> expectedSeats = new ArrayList<>();
	       
	        when(faresNSeatsService.setAllFareNSeats(anyList())).thenReturn(expectedSeats);

	       
	        List<FareNSeats> actualSeats = faresNSeatsUtil.createFareNSeats(1);

	       
	        assertEquals(expectedSeats, actualSeats);
	        
	    }

	    @Test
	    public void testGetFareByFlightIdAndClass() {
	      
	        FareNSeats mockFareNSeats = new FareNSeats();
	        mockFareNSeats.setFare(10000); // Set a mock fare value
	        when(faresNSeatsService.getFareByFlightIdAndClass(8, "Economy Class")).thenReturn(mockFareNSeats);

	     
	        Integer actualFare = faresNSeatsUtil.getFareByFlightIdAndClass(8, "Economy Class");

	       
	        assertEquals(10000, actualFare); 
	    }

	    @Test
	    public void testGetAvailableSeats() {
	      
	        List<FareNSeats> expectedAvailableSeats = new ArrayList<>();
	      
	        when(faresNSeatsService.getAvailableSeats(8, "Economy Class")).thenReturn(expectedAvailableSeats);

	
	        List<FareNSeats> actualAvailableSeats = faresNSeatsUtil.getAvailableSeats(8, "Economy Class");

	     
	        assertEquals(expectedAvailableSeats, actualAvailableSeats);
	        
	    }

	    @Test
	    public void testChangeAvailability() {
	   
	        faresNSeatsUtil.changeAvailability(8, "1A");

	       
	        verify(faresNSeatsService).changeAvailability(8, "1A");
	    }
}
