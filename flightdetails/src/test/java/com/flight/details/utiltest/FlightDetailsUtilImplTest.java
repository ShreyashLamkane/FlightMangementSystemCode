package com.flight.details.utiltest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.flight.details.entity.Flight;
import com.flight.details.external.services.FareNSeatsService;
import com.flight.details.service.DetailsService;
import com.flight.details.util.FlightDetailsUtilImpl;

@SpringBootTest(classes= {FlightDetailsUtilImplTest.class})
public class FlightDetailsUtilImplTest {
	@Mock
    private FareNSeatsService fareNSeatsService;

    @Mock
    private DetailsService detailsService;

    @InjectMocks
    private FlightDetailsUtilImpl flightDetailsUtil;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateFlight() {
        // Mocking data
        Flight flight = new Flight();
        flight.setFlightId(8);

        // Stubbing service method
        when(detailsService.createFlight(flight)).thenReturn(flight);


        when(fareNSeatsService.setSeatsNFare(flight.getFlightId()));

    
        Flight createdFlight = flightDetailsUtil.createFlight(flight);

        
        assertNotNull(createdFlight);
        assertEquals(flight.getFlightId(), createdFlight.getFlightId());
    }

    @Test
    public void testDeleteFlight() {
        // Mocking data
        Integer flightId = 8;

        // Stubbing service method
        when(detailsService.deleteFlight(flightId)).thenReturn(true);

        
        boolean isDeleted = flightDetailsUtil.deleteFlight(flightId);

      
        assertTrue(isDeleted);
    }

    @Test
    public void testUpdateFlight() {
        // Mocking data
        Flight flight = new Flight();
        flight.setFlightId(8);

        // Stubbing service method
        when(detailsService.createFlight(flight)).thenReturn(flight);

       
        Flight updatedFlight = flightDetailsUtil.updateFlight(flight);

        
        assertNotNull(updatedFlight);
        assertEquals(flight.getFlightId(), updatedFlight.getFlightId());
    }

    @Test
    public void testGetFlight() {
        // Mocking data
        String src = "Delhi";
        String dest = "Indore";
        LocalDate departureDate = LocalDate.now();
        List<Flight> expectedFlights = new ArrayList<>();

        // Stubbing service method
        when(detailsService.getFlight(src, dest, departureDate)).thenReturn(expectedFlights);

      
        List<Flight> flights = flightDetailsUtil.getFlight(src, dest, departureDate);

        
        assertNotNull(flights);
        assertEquals(expectedFlights, flights);
    }

    @Test
    public void testGetFlightById() {
        // Mocking data
        Integer flightId = 8;
        Flight flight = new Flight();
        flight.setFlightId(flightId);

        // Stubbing service method
        when(detailsService.getFlightById(flightId)).thenReturn(flight);

        // Calling util method
        Flight foundFlight = flightDetailsUtil.getFlightById(flightId);

       
        assertNotNull(foundFlight);
        assertEquals(flightId, foundFlight.getFlightId());
    }
}
