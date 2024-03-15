package com.orosales.microservices.commonmodels.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "licence")
public class LicenceQuery {

    //@Id
    private Integer idLicence;

    private String numDocumentClient;
    private String numLicence;
    private String typeLicence;
    private LocalDate dateIssue;
    private LocalDate dateExpired;
    private String status;
    private String nameCustomer;
}
