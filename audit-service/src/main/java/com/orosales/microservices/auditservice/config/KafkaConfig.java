package com.orosales.microservices.auditservice.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class KafkaConfig {

    @Value("${kafka.orosales.server:127.0.0.1}")
    private String kafkaServer;

    @Value("${kafka.orosales.port:9092}")
    private String kafkaPort;

    @Value("${kafka.orosales.topic:licence}")
    private String topicName;

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> kafkaProperties = new HashMap<>();
        kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer+":"+kafkaPort);
        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, topicName);

        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        kafkaProperties.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
        kafkaProperties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);


        kafkaProperties.put(JsonDeserializer.KEY_DEFAULT_TYPE, "com.orosales.microservices.auditservice.config.KafkaConfig");
        kafkaProperties.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.orosales.microservices.auditservice.config.KafkaConfig");
        kafkaProperties.put(JsonDeserializer.TRUSTED_PACKAGES, "com.orosales.microservices.*");

        return new DefaultKafkaConsumerFactory<>(kafkaProperties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory( consumerFactory() );
        return factory;
    }

    @KafkaListener(topics = "licence", groupId = "licence")
    public void listenTopic(Object obj) {
        log.info(obj.toString());
    }
}
