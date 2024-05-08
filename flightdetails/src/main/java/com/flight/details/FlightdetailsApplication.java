package com.flight.details;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "Flight Service"))
public class FlightdetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightdetailsApplication.class, args);
	}

}
