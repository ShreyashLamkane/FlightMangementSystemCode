package com.flight.faresnseats.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flight.faresnseats.Exception.ResourceNoFoundException;
import com.flight.faresnseats.entity.FareNSeats;
import com.flight.faresnseats.entity.Flight;
import com.flight.faresnseats.external.service.FlightDetails;
import com.flight.faresnseats.service.FaresNSeatsService;

@Component
public class FaresNSeatsUtilImpl implements  FaresNSeatsUtil{
	
	private static final Logger logger = LoggerFactory.getLogger(FaresNSeatsUtilImpl.class);
	
	@Autowired
	private FaresNSeatsService faresNSeatsService;
	
	@Autowired
	private FlightDetails flightDetails;
	
	public List<FareNSeats> createFareNSeats(Integer flightId) {
		
		logger.info("Creating fare and seats for flight with ID: {}", flightId);
		Flight flight=flightDetails.getFlightById(flightId);
		if(flight==null) {
			logger.error("Flight not found for ID: {}", flightId);
			throw new ResourceNoFoundException("Flight Not Found");
		}
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
        
        if (row <= 5) {
            return "First Class";
        } else if (row <= 10) {
            return "Business Class";
        } else {
            return "Economy Class";
        }
    }
	private double calculateFare(String seatClass,Integer baseFare) {
       
        if (seatClass.equals("Economy Class")) {
            return baseFare;
        } else if (seatClass.equals("Business Class")) {
            return 5 * baseFare; 
        } else {
            
            return 3*baseFare; 
        }
    }

	@Override
	public Integer getFareByFlightIdAndClass(Integer flightId, String seatClass) {
		// TODO Auto-generated method stub
		 logger.info("Fetching fare for flight with ID: {} and seat class: {}", flightId, seatClass);
		FareNSeats f= faresNSeatsService.getFareByFlightIdAndClass(flightId, seatClass);
		if(f==null) {
			logger.error("Fare not found for flight ID: {} and seat class: {}", flightId, seatClass);
			throw new ResourceNoFoundException("Seat Not Found By Flight Id and class");
		}
			
		Double d=f.getFare();
		return (int) d.doubleValue();
	}

	@Override
	public List<FareNSeats> getAvailableSeats(Integer flightId, String seatClass) {
		// TODO Auto-generated method stub
		logger.info("Fetching available seats for flight with ID: {} and seat class: {}", flightId, seatClass);
		return faresNSeatsService.getAvailableSeats(flightId, seatClass);
	}

	@Override
	public void changeAvailability(Integer flightId, String seatNo) {
		// TODO Auto-generated method stub
		 logger.info("Changing availability of seat {} for flight with ID: {}", seatNo, flightId);
		faresNSeatsService.changeAvailability(flightId, seatNo);
	}
	
	
	
}
