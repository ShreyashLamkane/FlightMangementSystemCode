package com.flight.flightsecurity.config;


import com.flight.flightsecurity.entity.CustomUserDetails;
import com.flight.flightsecurity.entity.UserCredentials;
import com.flight.flightsecurity.repository.UserCredentialsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
 
import java.util.Optional;
 
@Component
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserCredentialsRepository repository;
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredentials> credential = repository.findByName(username);
       // return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
        return credential.map(CustomUserDetails::new).get();
    }
}