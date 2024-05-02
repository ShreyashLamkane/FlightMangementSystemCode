package com.flight.faresnseats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.faresnseats.entity.FareNSeats;
import com.flight.faresnseats.repository.FareNSeatsRepository;

@Service
public class FaresNSeatsServiceImpl implements  FaresNSeatsService{
	
	@Autowired
	private FareNSeatsRepository fareNSeatsRepository;
	
	@Override
	public FareNSeats setFareNSeats(FareNSeats fareNSeats) {
		// TODO Auto-generated method stub
		return fareNSeatsRepository.save(fareNSeats);
	}
	
	public List<FareNSeats> setAllFareNSeats(List<FareNSeats> list){
		
		return fareNSeatsRepository.saveAll(list);
	}

}
