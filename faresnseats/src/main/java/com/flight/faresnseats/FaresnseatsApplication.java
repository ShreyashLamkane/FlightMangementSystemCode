package com.flight.faresnseats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FaresnseatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaresnseatsApplication.class, args);
	}

}
