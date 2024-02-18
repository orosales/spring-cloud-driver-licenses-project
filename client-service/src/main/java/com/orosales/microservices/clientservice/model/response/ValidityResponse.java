package com.orosales.microservices.clientservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidityResponse {
    private LocalDate dateIssue;
    private LocalDate dateExpired;
    private String status;


}
