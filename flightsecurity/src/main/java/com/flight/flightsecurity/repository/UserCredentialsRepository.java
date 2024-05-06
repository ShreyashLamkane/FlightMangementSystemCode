package com.flight.flightsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.flightsecurity.entity.UserCredentials;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Integer> {
	Optional<UserCredentials> findByName(String username);
}
