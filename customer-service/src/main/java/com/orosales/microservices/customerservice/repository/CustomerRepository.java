package com.orosales.microservices.customerservice.repository;

import com.orosales.microservices.customerservice.model.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findByNumIdentification(String numIdentification);
}
