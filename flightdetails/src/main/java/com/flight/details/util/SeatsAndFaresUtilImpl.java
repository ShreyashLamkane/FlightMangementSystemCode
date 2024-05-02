package com.flight.details.util;

public class SeatsAndFaresUtilImpl {
	
	
	   public List<Seat> addSeatsToFlight(Long flightId, int numRows, int seatsPerRow) {
	        List<Seat> seats = new ArrayList<>();
	        char seatLetter = 'A';

	        for (int row = 1; row <= numRows; row++) {
	            for (int seat = 1; seat <= seatsPerRow; seat++) {
	                Seat newSeat = new Seat();
	                newSeat.setFlightId(flightId);
	                newSeat.setSeatNumber(row + String.valueOf(seatLetter));
	                newSeat.setSeatClass(calculateSeatClass(row)); // Calculate seat class based on row
	                newSeat.setFare(calculateFare(newSeat.getSeatClass())); // Calculate fare based on seat class
	                newSeat.setAvailable(true);
	                seats.add(newSeat);
	            }
	            seatLetter++; // Increment seat letter
	        }

	        return seatRepository.saveAll(seats);
	    }
}
