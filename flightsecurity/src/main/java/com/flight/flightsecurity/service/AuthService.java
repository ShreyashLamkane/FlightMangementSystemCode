package com.flight.flightsecurity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flight.flightsecurity.entity.UserCredentials;
import com.flight.flightsecurity.repository.UserCredentialsRepository;

@Service
public class AuthService {

	@Autowired
	private UserCredentialsRepository userCredentialsRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	// Saving the user in database by encoding password
	public String saveUser(UserCredentials credential) {

		credential.setPassword(passwordEncoder.encode(credential.getPassword()));

		userCredentialsRepository.save(credential);
		return "User Added to system";

	}

	// Generating the token by username
	public String generateToken(String username) {
		Optional<UserCredentials> credentials=userCredentialsRepository.findByName(username);
		return jwtService.generateToken(username,credentials.get().getRole());
	}

	// Validating the token
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}

}
