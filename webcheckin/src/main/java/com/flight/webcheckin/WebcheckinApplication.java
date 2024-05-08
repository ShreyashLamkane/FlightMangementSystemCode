package com.flight.webcheckin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "Web Check In Service"))
public class WebcheckinApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebcheckinApplication.class, args);
	}

}
