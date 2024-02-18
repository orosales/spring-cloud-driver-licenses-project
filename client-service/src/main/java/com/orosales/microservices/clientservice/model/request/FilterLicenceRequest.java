package com.orosales.microservices.clientservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterLicenceRequest {
    private String status;
    private String typeLicence;
    private String numLicence;


}
