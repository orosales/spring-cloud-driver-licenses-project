package com.orosales.microservices.driverlicenceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.orosales.microservices.driverlicenceservice","com.orosales.microservices.commonmodels"  })
public class DriverLicenceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriverLicenceServiceApplication.class, args);
	}

}
