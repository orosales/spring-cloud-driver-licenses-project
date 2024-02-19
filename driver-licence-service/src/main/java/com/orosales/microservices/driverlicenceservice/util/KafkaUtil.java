package com.orosales.microservices.driverlicenceservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaUtil {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.orosales.topic:licence}")
    private String topicName;

    public void sendMessage(Object obj) {
        kafkaTemplate.send(topicName, obj);
    }
}
