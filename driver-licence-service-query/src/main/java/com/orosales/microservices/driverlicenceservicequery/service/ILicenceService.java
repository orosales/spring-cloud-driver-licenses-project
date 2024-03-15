package com.orosales.microservices.driverlicenceservicequery.service;


import com.orosales.microservices.commonmodels.model.entity.LicenceQuery;
import com.orosales.microservices.driverlicenceservicequery.dto.FilterLicenceDTO;
import com.orosales.microservices.driverlicenceservicequery.dto.ValidityDTO;

import java.util.List;
import java.util.Map;

public interface ILicenceService {

    List<LicenceQuery> list() throws Exception;
    LicenceQuery listById(Integer id) throws Exception;
    ValidityDTO validateLicence(String numLicence) throws Exception;
    List<LicenceQuery> listByFilter(FilterLicenceDTO filter) throws Exception;
}
