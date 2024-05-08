package com.flight.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Gateway"))
public class FlightGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightGatewayApplication.class, args);
	}

}
