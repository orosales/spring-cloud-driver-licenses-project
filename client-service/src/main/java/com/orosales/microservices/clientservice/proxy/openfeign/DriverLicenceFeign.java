package com.orosales.microservices.clientservice.proxy.openfeign;

import com.orosales.microservices.clientservice.model.request.FilterLicenceRequest;
import com.orosales.microservices.clientservice.model.request.LicenceRequest;
import com.orosales.microservices.clientservice.model.response.LicenceResponse;
import com.orosales.microservices.clientservice.model.response.ValidityResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "driver-licence-service")
public interface DriverLicenceFeign {

    @PostMapping("/licences")
    LicenceResponse registerLicence(@RequestBody LicenceRequest licence);

    @GetMapping("/licences")
    List<LicenceResponse> listAll();

    @GetMapping("/licences/validity/{numLicence}")
    ValidityResponse validateLicence(@PathVariable("numLicence") String numLicence  );


    @GetMapping("/licences/list/byFilter")
    List<LicenceResponse> listByFilter(@RequestParam(required = false) String status,
                                       @RequestParam(required = false) String typeLicence,
                                       @RequestParam(required = false) String numLicence);

    @RequestMapping(path= "/licences/{idLicence}", method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE    )
    LicenceResponse updateLicence(@PathVariable("idLicence") Integer idLicence,
                                  @RequestBody Map<String, Object> fields);

    @PutMapping("/licences/eliminateExpired")
    List<LicenceResponse> eliminateExpiredLicences();


}
