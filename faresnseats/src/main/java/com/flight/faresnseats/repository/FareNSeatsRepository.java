package com.flight.faresnseats.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.faresnseats.entity.FareNSeats;

public interface FareNSeatsRepository extends JpaRepository<FareNSeats, Integer> {

}
