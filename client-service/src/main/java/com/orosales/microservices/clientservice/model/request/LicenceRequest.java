package com.orosales.microservices.clientservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LicenceRequest {

    private Integer idLicence;
    private String numDocumentClient;
    private String numLicence;
    private String typeLicence;
    private LocalDate dateIssue;
    private LocalDate dateExpired;
    private String status;
    private String nameCustomer;
}
