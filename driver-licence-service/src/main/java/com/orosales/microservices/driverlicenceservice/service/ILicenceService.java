package com.orosales.microservices.driverlicenceservice.service;

import com.orosales.microservices.driverlicenceservice.dto.FilterLicenceDTO;
import com.orosales.microservices.driverlicenceservice.dto.ValidityDTO;
import com.orosales.microservices.driverlicenceservice.model.Licence;

import java.util.List;
import java.util.Map;

public interface ILicenceService {
    Licence register(Licence licence) throws Exception;
    Licence modify(Licence licence) throws Exception;
    List<Licence> list() throws Exception;
    void eliminate(Integer id) throws Exception;
    Licence listById(Integer id) throws Exception;
    ValidityDTO validateLicence(String numLicence) throws Exception;
    List<Licence> listByFilter(FilterLicenceDTO filter) throws Exception;
    Licence modifyByFields(Integer id, Map<String, Object> fields) throws Exception;
    List<Licence> eliminateExpiredLicences() throws Exception;
}
