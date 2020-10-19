package com.chargemanag1.bankmanag1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.chargemanag1.bankmanag1.*"})
@SpringBootApplication
public class Bankmanag1Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Bankmanag1Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Bankmanag1Application.class, args);
		LOGGER.info("Server successfully started");
	}

}
