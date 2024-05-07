package com.flight.webcheckin.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.flight.webcheckin.entity.Passengers;



//@FeignClient(name="BOOKING-SERVICE")
public interface PassengersService {
//	@PutMapping("/passengers/update")
//	public ResponseEntity<Passengers> updatePassenger(@RequestBody Passengers passengers) ;
}
