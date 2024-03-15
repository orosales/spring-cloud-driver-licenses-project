package com.orosales.microservices.driverlicenceservicequery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.orosales.microservices.driverlicenceservicequery","com.orosales.microservices.commonmodels"  })
public class DriverLicenceServiceQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriverLicenceServiceQueryApplication.class, args);
	}

}
