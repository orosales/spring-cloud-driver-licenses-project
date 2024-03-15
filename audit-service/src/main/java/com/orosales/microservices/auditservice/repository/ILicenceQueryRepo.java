package com.orosales.microservices.auditservice.repository;

import com.orosales.microservices.commonmodels.model.entity.LicenceQuery;
import org.springframework.data.repository.CrudRepository;

public interface ILicenceQueryRepo extends CrudRepository<LicenceQuery, Integer> {
}
