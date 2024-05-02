package com.flight.externalserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ExternalconfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExternalconfigApplication.class, args);
	}

}
