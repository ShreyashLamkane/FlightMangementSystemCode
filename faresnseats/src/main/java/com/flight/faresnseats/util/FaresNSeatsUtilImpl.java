package com.flight.faresnseats.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flight.faresnseats.entity.FareNSeats;
import com.flight.faresnseats.entity.Flight;
import com.flight.faresnseats.external.service.FlightDetails;
import com.flight.faresnseats.service.FaresNSeatsService;

@Component
public class FaresNSeatsUtilImpl implements  FaresNSeatsUtil{
	
	@Autowired
	private FaresNSeatsService faresNSeatsService;
	
	@Autowired
	private FlightDetails flightDetails;
	
	public List<FareNSeats> createFareNSeats(Integer flightId) {
		
		
		Flight flight=flightDetails.getFlightById(flightId);
		int numsRow=flight.getNumRows();
		int seatsPerRow=flight.getSeatsPerRow();
		 List<FareNSeats> seats = new ArrayList<FareNSeats>();
	     char seatLetter = 'A';
	     
	     for (int row = 1; row <= numsRow; row++) {
	            for (int seat = 1; seat <= seatsPerRow; seat++) {
	            	FareNSeats newSeat=new FareNSeats();
	            	newSeat.setFlightId(flightId);
	            	newSeat.setSeatNumber(row + String.valueOf(seatLetter));
	            	newSeat.setSeatClass(calculateSeatClass(row));
	            	
	            	newSeat.setFare(calculateFare(newSeat.getSeatClass(),flight.getFare()));
	            	newSeat.setAvailable(true);
	            	seats.add(newSeat);
	            	seatLetter++;
	            }
	            seatLetter='A';
	     }
		return faresNSeatsService.setAllFareNSeats(seats);
	}
	
	private String calculateSeatClass(int row) {
        // For example, consider the first few rows as first class, then business, then economy
        if (row <= 5) {
            return "First Class";
        } else if (row <= 10) {
            return "Business Class";
        } else {
            return "Economy Class";
        }
    }
	private double calculateFare(String seatClass,Integer baseFare) {
        // Base fare for economy class is 500, for business class, it is 3 times the base fare
        if (seatClass.equals("Economy Class")) {
            return baseFare;
        } else if (seatClass.equals("Business Class")) {
            return 5 * baseFare; // Three times the base fare
        } else {
            // Assuming first class fare is different from the others
            // You can define your own logic here
            return 3*baseFare; // Example fare for first class
        }
    }

	@Override
	public Integer getFareByFlightIdAndClass(Integer flightId, String seatClass) {
		// TODO Auto-generated method stub
		FareNSeats f= faresNSeatsService.getFareByFlightIdAndClass(flightId, seatClass);
		Double d=f.getFare();
		return (int) d.doubleValue();
	}

	@Override
	public List<FareNSeats> getAvailableSeats(Integer flightId, String seatClass) {
		// TODO Auto-generated method stub
		
		return faresNSeatsService.getAvailableSeats(flightId, seatClass);
	}

}
