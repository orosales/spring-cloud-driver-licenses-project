package com.orosales.microservices.driverlicenceservicecommand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.orosales.microservices.driverlicenceservicecommand","com.orosales.microservices.commonmodels"  })

public class DriverLicenceServiceCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriverLicenceServiceCommandApplication.class, args);
	}

}
