package com.orosales.microservices.auditservice.config;


import com.orosales.microservices.auditservice.repository.IAuditRepo;
import com.orosales.microservices.auditservice.repository.ILicenceQueryRepo;
import com.orosales.microservices.commonmodels.model.entity.AuditInfo;
import com.orosales.microservices.commonmodels.model.entity.Licence;
import com.orosales.microservices.commonmodels.model.entity.LicenceQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.orosales.microservices.commonmodels.model.entity.GenericEntity;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Value("${kafka.orosales.server:127.0.0.1}")
    private String kafkaServer;

    @Value("${kafka.orosales.port:9092}")
    private String kafkaPort;

    @Value("${kafka.orosales.topic:licence}")
    private String topicName;


    @Autowired
    private IAuditRepo auditRepository;

    @Autowired
    private ILicenceQueryRepo licenceQueryRepository;

    @Bean
    public ConsumerFactory<String, GenericEntity<? extends GenericEntity>> consumerFactory() {
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
    public ConcurrentKafkaListenerContainerFactory<String, GenericEntity<? extends GenericEntity>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, GenericEntity<? extends GenericEntity>> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory( consumerFactory() );
        return factory;
    }

    @KafkaListener(topics = "licence", groupId = "licence")
    public void listenTopic(GenericEntity<? extends GenericEntity> obj) {
        log.info("ListenTopic");
        if (obj instanceof Licence licence) {
            log.info("Saving Licence");
            LicenceQuery licenceQuery = new LicenceQuery();
            BeanUtils.copyProperties(licence, licenceQuery);
            log.info("Finished to copy all values from Licence to QueryLicence");
            log.info("licenceQuery to String: " +licenceQuery.toString() );
            licenceQueryRepository.save(licenceQuery);
            log.info(licence.toString());
        } else if (obj instanceof AuditInfo auditInfo) {
            log.info("Saving auditInfo");
            auditRepository.save(auditInfo);
            log.info(auditInfo.toString());
        }

    }
}
