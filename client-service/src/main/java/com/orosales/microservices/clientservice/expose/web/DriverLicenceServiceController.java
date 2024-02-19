package com.orosales.microservices.clientservice.expose.web;

import com.orosales.microservices.clientservice.model.request.FilterLicenceRequest;
import com.orosales.microservices.clientservice.model.request.LicenceRequest;
import com.orosales.microservices.clientservice.model.response.ValidityResponse;
import lombok.RequiredArgsConstructor;
import com.orosales.microservices.clientservice.model.response.LicenceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.orosales.microservices.clientservice.service.LicenceDriverService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/licences")
@RequiredArgsConstructor
public class DriverLicenceServiceController {

    private final LicenceDriverService licenceDriverService;

    @GetMapping
    public List<LicenceResponse> listAll() {
        return licenceDriverService.listAll();
    }


    @PostMapping
    public ResponseEntity<LicenceResponse> registerLicence(@RequestBody LicenceRequest licence) {
        return new ResponseEntity(licenceDriverService.register(licence), HttpStatus.CREATED);
    }

    @GetMapping("/validity/{numLicence}")
    public ResponseEntity<ValidityResponse> validateLicence(@PathVariable("numLicence") String numLicence  )throws Exception {
        return  ResponseEntity.ok (licenceDriverService.validateLicence(numLicence));
    }


    @GetMapping("/list/byFilter")
    public ResponseEntity<List<LicenceResponse>> listByFilter(@RequestParam(name = "status", required = false) String status,
                                                              @RequestParam(name="typeLicence", required = false) String typeLicence,
                                                              @RequestParam(name="numLicence", required = false) String numLicence) {


        return  ResponseEntity.ok(  licenceDriverService.listByFilter(status, typeLicence, numLicence) );
    }



    @PatchMapping(path="/{idLicence}")
    public ResponseEntity<LicenceResponse> updateLicence(@PathVariable("idLicence") Integer idLicence,
                                                 @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok ( licenceDriverService.modifyByFields(idLicence, fields) ) ;
    }

}
