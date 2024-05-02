package com.flight.details;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FlightdetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightdetailsApplication.class, args);
	}

}
