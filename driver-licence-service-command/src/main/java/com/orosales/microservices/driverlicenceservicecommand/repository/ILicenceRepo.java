package com.orosales.microservices.driverlicenceservicecommand.repository;

//import com.orosales.microservices.driverlicenceservice.model.Licence;

import com.orosales.microservices.commonmodels.model.entity.Licence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface ILicenceRepo extends JpaRepository<Licence, Integer > {


      List<Licence> findByStatusAndDateExpiredLessThan(String status, LocalDate localDate);
}
