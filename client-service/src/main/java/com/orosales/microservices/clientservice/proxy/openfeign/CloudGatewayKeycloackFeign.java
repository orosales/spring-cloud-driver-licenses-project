package com.orosales.microservices.clientservice.proxy.openfeign;

import com.orosales.microservices.clientservice.model.request.LicenceRequest;
import com.orosales.microservices.clientservice.model.response.LicenceResponse;
import com.orosales.microservices.clientservice.model.response.ValidityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "cloud-gateway")
public interface CloudGatewayKeycloackFeign {

    @PostMapping("/api/driver-licence-service/licences")
    LicenceResponse registerLicence(@RequestBody LicenceRequest licence);

    @GetMapping("/api/driver-licence-service/licences")
    List<LicenceResponse> listAll();

    @GetMapping("/api/driver-licence-service/licences/validity/{numLicence}")
    ValidityResponse validateLicence(@PathVariable("numLicence") String numLicence  );


    @GetMapping("/api/driver-licence-service/licences/list/byFilter")
    List<LicenceResponse> listByFilter(@RequestParam(required = false) String status,
                                       @RequestParam(required = false) String typeLicence,
                                       @RequestParam(required = false) String numLicence);

    @RequestMapping(path= "/api/driver-licence-service/licences/{idLicence}", method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE    )
    LicenceResponse updateLicence(@PathVariable("idLicence") Integer idLicence,
                                  @RequestBody Map<String, Object> fields);

    @PutMapping("/api/driver-licence-service/licences/eliminateExpired")
    List<LicenceResponse> eliminateExpiredLicences();

}
