package com.orosales.microservices.driverlicenceservice.repository;


import com.orosales.microservices.commonmodels.model.entity.Licence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface ILicenceRepo extends JpaRepository<Licence, Integer > {

    Licence findByNumLicence(String numLicence);
    Licence findByTypeLicence(String typeLicence);
    List<Licence> findByStatusOrTypeLicenceOrNumLicence(String status, String typeLicence, String numLicence);
    List<Licence> findByStatusAndDateExpiredLessThan(String status, LocalDate localDate);
}
