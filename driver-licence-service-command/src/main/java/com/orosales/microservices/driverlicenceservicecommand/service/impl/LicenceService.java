package com.orosales.microservices.driverlicenceservicecommand.service.impl;

import com.orosales.microservices.commonmodels.model.entity.AuditInfo;
import com.orosales.microservices.commonmodels.model.entity.Licence;
import com.orosales.microservices.driverlicenceservicecommand.dto.FilterLicenceDTO;
import com.orosales.microservices.driverlicenceservicecommand.dto.ValidityDTO;
import com.orosales.microservices.driverlicenceservicecommand.repository.ILicenceRepo;
import com.orosales.microservices.driverlicenceservicecommand.util.Constants;
import com.orosales.microservices.driverlicenceservicecommand.util.KafkaUtil;
import com.orosales.microservices.driverlicenceservicecommand.service.ILicenceService;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
    public void eliminate(Integer id) throws Exception {
        repo.deleteById(id);
    }




    @Override
    public Licence modifyByFields(Integer id, Map<String, Object> fields)  {
        Optional<Licence> existingLicence = repo.findById(id);

        if (existingLicence.isPresent()) {
            fields.forEach((key, value) -> {
                        Field field = ReflectionUtils.findField(Licence.class, key);
                        if (field!=null) {
                            field.setAccessible(true);

                            if (key.contains("date")) {
                                String dateString = (String) value;
                                LocalDate dateValue = LocalDate.parse( dateString, DateTimeFormatter.ISO_DATE_TIME );
                                ReflectionUtils.setField(field, existingLicence.get(), dateValue);
                            } else {
                                ReflectionUtils.setField(field, existingLicence.get(), value);
                            }
                        }

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
