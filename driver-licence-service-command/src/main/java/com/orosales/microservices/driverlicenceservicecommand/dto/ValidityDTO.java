package com.orosales.microservices.driverlicenceservicecommand.dto;

import java.time.LocalDate;

public class ValidityDTO {
    private LocalDate dateIssue;
    private LocalDate dateExpired;
    private String status;

    public LocalDate getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(LocalDate dateIssue) {
        this.dateIssue = dateIssue;
    }

    public LocalDate getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(LocalDate dateExpired) {
        this.dateExpired = dateExpired;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
