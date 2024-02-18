package com.orosales.microservices.driverlicenceservice.repository;

import com.orosales.microservices.driverlicenceservice.model.Licence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface ILicenceRepo extends JpaRepository<Licence, Integer > {

    Licence findByNumLicence(String numLicence);
    Licence findByTypeLicence(String typeLicence);
    List<Licence> findByStatusOrTypeLicenceOrNumLicence(String status, String typeLicence, String numLicence);
    List<Licence> findByStatusAndDateExpiredLessThan(String status, LocalDate localDate);
}
