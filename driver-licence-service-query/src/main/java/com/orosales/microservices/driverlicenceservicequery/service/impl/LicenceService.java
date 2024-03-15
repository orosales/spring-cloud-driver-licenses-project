package com.orosales.microservices.driverlicenceservicequery.service.impl;

import com.orosales.microservices.commonmodels.model.entity.LicenceQuery;
import com.orosales.microservices.driverlicenceservicequery.dto.FilterLicenceDTO;
import com.orosales.microservices.driverlicenceservicequery.dto.ValidityDTO;
import com.orosales.microservices.driverlicenceservicequery.repository.ILicenceQueryRepo;
import com.orosales.microservices.driverlicenceservicequery.service.ILicenceService;
import com.orosales.microservices.driverlicenceservicequery.util.Constants;
import com.orosales.microservices.driverlicenceservicequery.util.KafkaUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class LicenceService implements ILicenceService {

    private ILicenceQueryRepo repo;

    private KafkaUtil kafkaUtil;

    LicenceService(ILicenceQueryRepo repo, KafkaUtil kafkaUtil) {
        this.repo = repo;
        this.kafkaUtil = kafkaUtil;

    }


    @Override
    public List<LicenceQuery> list() throws Exception {
        Iterable<LicenceQuery> licences= repo.findAll();
        List<LicenceQuery> result = new ArrayList<>();
        licences.forEach( result:: add);
        return result;
    }



    @Override
    public LicenceQuery listById(Integer id) throws Exception {
        Optional<LicenceQuery> op= repo.findById(id);
        return op.isPresent()? op.get() : new LicenceQuery();
    }

    @Override
    public ValidityDTO validateLicence(String numLicence) throws Exception {
        ValidityDTO validityDTO = new ValidityDTO();
        LicenceQuery licence = repo.findByNumLicence(numLicence);
        ZoneId zone = ZoneId.of("America/Halifax"); //
        LocalDate currentDate = LocalDate.now(zone);
        String currentStatus;
        if ( currentDate.isBefore( licence.getDateExpired() ) &&
                currentDate.isAfter( licence.getDateIssue())  ) {
            currentStatus = Constants.ACTIVE;
        } else {
            currentStatus = Constants.INACTIVE;
            licence.setStatus(currentStatus);
            if (licence.getStatus().equals(Constants.ACTIVE))
                repo.save(licence);
        }


        validityDTO.setStatus(currentStatus);
        validityDTO.setDateIssue( licence.getDateIssue() );
        validityDTO.setDateExpired( licence.getDateExpired() );
        return validityDTO;
    }

    @Override
    public List<LicenceQuery> listByFilter(FilterLicenceDTO filter) throws Exception {
        List<LicenceQuery> licences = repo.findByStatusOrTypeLicenceOrNumLicence(
                                                filter.getStatus(),
                                                filter.getTypeLicence(),
                                                filter.getNumLicence());
        return licences;
    }





}
