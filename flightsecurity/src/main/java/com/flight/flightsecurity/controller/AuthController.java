package com.flight.flightsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.flightsecurity.dto.AuthRequest;
import com.flight.flightsecurity.entity.UserCredentials;
import com.flight.flightsecurity.exception.AuthenticationFailedException;
import com.flight.flightsecurity.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService service;

	// to authenticate that the user data passed to generate token is present in
	// credentials or not
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public String addNewUser(@RequestBody UserCredentials user) {

		return service.saveUser(user);
	}

	@PostMapping("/token")
	public String getToken(@RequestBody AuthRequest user) throws AuthenticationFailedException {
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//		return service.generateToken(user.getUsername());
		System.out.println(" is null? " + authenticate.isAuthenticated());
		if (authenticate.isAuthenticated()) {
			return service.generateToken(user.getUsername());
		} else {
			throw new AuthenticationFailedException("Invalid access, Credentials not found. ");
		}
//		return service.generateToken(user.getUsername());
	}

	@GetMapping("/validate")
	public String validateToken(@RequestParam String token) {

		service.validateToken(token);
		return "Token is valid";
	}
}
