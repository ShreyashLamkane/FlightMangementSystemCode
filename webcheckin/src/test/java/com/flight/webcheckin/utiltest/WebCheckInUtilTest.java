package com.flight.webcheckin.utiltest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.flight.webcheckin.entity.Bookings;
import com.flight.webcheckin.entity.FareNSeats;
import com.flight.webcheckin.entity.Passengers;
import com.flight.webcheckin.entity.Seat;
import com.flight.webcheckin.external.service.BookingService;
import com.flight.webcheckin.external.service.FareNSeatsService;
import com.flight.webcheckin.service.SeatService;
import com.flight.webcheckin.util.WebCheckInUtilImpl;


@SpringBootTest(classes= {WebCheckInUtilTest.class})
public class WebCheckInUtilTest {
    @Mock
    private SeatService seatService;

    @Mock
    private BookingService bookingService;

    @Mock
    private FareNSeatsService fareNSeatsService;

    @InjectMocks
    private WebCheckInUtilImpl webCheckInUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAvailableSeats() {
        
        List<FareNSeats> seats = new ArrayList<>();
       
        when(fareNSeatsService.getAvailableSeats(anyInt(), anyString())).thenReturn(seats);

  
        List<FareNSeats> result = webCheckInUtil.getAvailableSeats(8, "Economy Class");

        assertTrue(result.size() >1);
    }

    @Test
    public void testSeatAllocation() {
        // Mocking data
        String bookingId = "6634c98855e327057edccab2";
        Bookings booking = new Bookings();
        booking.setBookingId(bookingId);
        booking.setFlightId(8);
        booking.setSeatClass("Economy Class");
        List<Passengers> passengers = new ArrayList<>();
       
        when(bookingService.getBookingById(bookingId)).thenReturn(booking);
        booking.setPassenger((ArrayList<Passengers>) passengers);

        
        List<FareNSeats> seats = new ArrayList<>();
        when(fareNSeatsService.getAvailableSeats(booking.getFlightId(), booking.getSeatClass())).thenReturn(seats);
        
        when(seatService.addSeat(any(Seat.class))).thenReturn(new Seat());
        
        
        webCheckInUtil.seatAllocation(bookingId);
        
       
    }

    @Test
    public void testGetSeatByBookingId() {
        // Mocking data
        String bookingId = "6634c98855e327057edccab2";
        List<Seat> seats = new ArrayList<>();
       
        when(seatService.getSeatByBookingId(bookingId)).thenReturn(seats);

        
        List<Seat> result = webCheckInUtil.getSeatByBookingId(bookingId);

        assertTrue(result.size() >1);
    }
}
