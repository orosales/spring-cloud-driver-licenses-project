package com.orosales.microservices.clientservice.configuration;

import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZipkinConfig {

    @Bean
    public Capability configCapability(MeterRegistry registry) {
        return new MicrometerCapability(registry);
    }
}
