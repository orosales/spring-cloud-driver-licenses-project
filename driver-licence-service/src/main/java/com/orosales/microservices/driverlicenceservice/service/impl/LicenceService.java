package com.orosales.microservices.driverlicenceservice.service.impl;

import com.orosales.microservices.commonmodels.model.entity.AuditInfo;
import com.orosales.microservices.driverlicenceservice.dto.FilterLicenceDTO;
import com.orosales.microservices.driverlicenceservice.dto.ValidityDTO;
import com.orosales.microservices.commonmodels.model.entity.Licence;

import com.orosales.microservices.driverlicenceservice.repository.ILicenceRepo;
import com.orosales.microservices.driverlicenceservice.service.ILicenceService;
import com.orosales.microservices.driverlicenceservice.util.Constants;
import com.orosales.microservices.driverlicenceservice.util.KafkaUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LicenceService implements ILicenceService {

    private ILicenceRepo repo;

    private KafkaUtil kafkaUtil;

    LicenceService(ILicenceRepo repo, KafkaUtil kafkaUtil) {
        this.repo = repo;
        this.kafkaUtil = kafkaUtil;

    }

    @Override
    public Licence register(Licence licence) throws Exception {
        licence = repo.save(licence);
        kafkaUtil.sendMessage(licence);
        kafkaUtil.sendMessage(AuditInfo.builder()
                .appCallerName("Licence Service")
                .currentTimestampAction(System.currentTimeMillis())
                .message("Testing Kafka from Licence Service")
                .opnNumber(licence.getIdLicence().toString() )
                .statusCode("200")
                .build() );
        return licence;
    }

    @Override
    public Licence modify(Licence licence) throws Exception {
        return repo.save(licence);
    }



    @Override
    public List<Licence> list() throws Exception {
        return repo.findAll();
    }

    @Override
    public void eliminate(Integer id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Licence listById(Integer id) throws Exception {
        Optional<Licence> op= repo.findById(id);
        return op.isPresent()? op.get() : new Licence();
    }

    @Override
    public ValidityDTO validateLicence(String numLicence) throws Exception {
        ValidityDTO validityDTO = new ValidityDTO();
        Licence licence = repo.findByNumLicence(numLicence);
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
    public List<Licence> listByFilter(FilterLicenceDTO filter) throws Exception {
        List<Licence> licences = repo.findByStatusOrTypeLicenceOrNumLicence(
                                                filter.getStatus(),
                                                filter.getTypeLicence(),
                                                filter.getNumLicence());
        return licences;
    }

    @Override
    public Licence modifyByFields(Integer id, Map<String, Object> fields)  {
        Optional<Licence> existingLicence = repo.findById(id);

        if (existingLicence.isPresent()) {
            fields.forEach((key, value) -> {
                        Field field = ReflectionUtils.findField(Licence.class, key);
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, existingLicence.get(), value);
                    }

            );
            return repo.save(existingLicence.get());
        }

        return null;
    }

    @Override
    public List<Licence> eliminateExpiredLicences() throws Exception {
        List<Licence> listLicencesExpired = new ArrayList<>();
        ZoneId zone = ZoneId.of("America/Halifax");
        LocalDate currentDate = LocalDate.now(zone);
        List<Licence> byDateExpiredGreaterThan = repo.findByStatusAndDateExpiredLessThan(Constants.ACTIVE, currentDate.minusDays(30));

        byDateExpiredGreaterThan.forEach( licence -> {
            licence.setStatus(Constants.INACTIVE);
            repo.save(licence);
            listLicencesExpired.add(licence);
        });


        return listLicencesExpired;
    }


}
