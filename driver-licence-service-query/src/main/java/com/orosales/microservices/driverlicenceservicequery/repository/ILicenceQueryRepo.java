package com.orosales.microservices.driverlicenceservicequery.repository;


import com.orosales.microservices.commonmodels.model.entity.LicenceQuery;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ILicenceQueryRepo extends CrudRepository<LicenceQuery, Integer> {
    LicenceQuery findByNumLicence(String numLicence);
    LicenceQuery findByTypeLicence(String typeLicence);
    List<LicenceQuery> findByStatusOrTypeLicenceOrNumLicence(String status, String typeLicence, String numLicence);
    List<LicenceQuery> findByStatusAndDateExpiredLessThan(String status, LocalDate localDate);
}
