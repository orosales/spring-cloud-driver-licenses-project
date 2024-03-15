package com.orosales.microservices.driverlicenceservicecommand.service;

import com.orosales.microservices.commonmodels.model.entity.Licence;
import com.orosales.microservices.driverlicenceservicecommand.dto.FilterLicenceDTO;
import com.orosales.microservices.driverlicenceservicecommand.dto.ValidityDTO;

import java.util.List;
import java.util.Map;

public interface ILicenceService {
    Licence register(Licence licence) throws Exception;
    Licence modify(Licence licence) throws Exception;

    void eliminate(Integer id) throws Exception;

    Licence modifyByFields(Integer id, Map<String, Object> fields) throws Exception;
    List<Licence> eliminateExpiredLicences() throws Exception;
}
