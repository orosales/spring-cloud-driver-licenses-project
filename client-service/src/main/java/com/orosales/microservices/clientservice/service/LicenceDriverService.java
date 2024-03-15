package com.orosales.microservices.clientservice.service;

import com.orosales.microservices.clientservice.model.request.LicenceRequest;
import com.orosales.microservices.clientservice.model.response.ValidityResponse;
import com.orosales.microservices.clientservice.proxy.openfeign.CloudGatewayFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.orosales.microservices.clientservice.model.response.LicenceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LicenceDriverService {

    private final CloudGatewayFeign cloudGatewayFeign;


    public List<LicenceResponse> listAll() {
        return cloudGatewayFeign.listAll();
       }

    public LicenceResponse register(LicenceRequest licence) {
        return cloudGatewayFeign.registerLicence(licence);
    }

    public ValidityResponse validateLicence(String numLicence) {
        return cloudGatewayFeign.validateLicence(numLicence);
    }


    public List<LicenceResponse> listByFilter(String status, String typeLicence, String numLicence) {
        return cloudGatewayFeign.listByFilter(status, typeLicence, numLicence);
    }


    public LicenceResponse modifyByFields(Integer id, Map<String, Object> fields)  {
        return cloudGatewayFeign.updateLicence(id, fields);
    }

    /*
     *  Commands
     */
    public LicenceResponse registerLicenceCommand(LicenceRequest licence) {
        return cloudGatewayFeign.registerLicenceCommand(licence);
    }


    public LicenceResponse updateLicenceCommand(Integer idLicence,
                                                                @RequestBody Map<String, Object> fields) {
        return cloudGatewayFeign.updateLicenceCommand(idLicence,fields);
    }

    public List<LicenceResponse> eliminateExpiredLicencesCommand() {
        return cloudGatewayFeign.eliminateExpiredLicencesCommand();
    }

    /*
     *  Query
     */

    public List<LicenceResponse> listAllQuery() {
        return cloudGatewayFeign.listAllQuery();
    }

    public ValidityResponse validateLicenceQuery(String numLicence) {
        return cloudGatewayFeign.validateLicenceQuery(numLicence);
    }

    public List<LicenceResponse> listByFilterQuery(String status, String typeLicence, String numLicence) {
        return cloudGatewayFeign.listByFilterQuery(status, typeLicence, numLicence);
    }


}
