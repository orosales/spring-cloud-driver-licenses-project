package com.orosales.microservices.clientservice.service;

import com.orosales.microservices.clientservice.model.request.FilterLicenceRequest;
import com.orosales.microservices.clientservice.model.request.LicenceRequest;
import com.orosales.microservices.clientservice.model.response.ValidityResponse;
import com.orosales.microservices.clientservice.proxy.openfeign.CloudGatewayKeycloackFeign;
import com.orosales.microservices.clientservice.proxy.openfeign.DriverLicenceFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.orosales.microservices.clientservice.model.response.LicenceResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LicenceDriverService {

    //private final DriverLicenceFeign driverLicenceFeign;
    private final CloudGatewayKeycloackFeign cloudGatewayKeycloackFeign;


    public List<LicenceResponse> listAll() {
        return cloudGatewayKeycloackFeign.listAll();
       }

    public LicenceResponse register(LicenceRequest licence) {
        return cloudGatewayKeycloackFeign.registerLicence(licence);
    }

    public ValidityResponse validateLicence(String numLicence) {
        return cloudGatewayKeycloackFeign.validateLicence(numLicence);
    }


    public List<LicenceResponse> listByFilter(String status, String typeLicence, String numLicence) {
        System.out.println("Client - Service - ListByFilter ");
        return cloudGatewayKeycloackFeign.listByFilter(status, typeLicence, numLicence);
    }


    public LicenceResponse modifyByFields(Integer id, Map<String, Object> fields)  {
        return cloudGatewayKeycloackFeign.updateLicence(id, fields);
    }


}
