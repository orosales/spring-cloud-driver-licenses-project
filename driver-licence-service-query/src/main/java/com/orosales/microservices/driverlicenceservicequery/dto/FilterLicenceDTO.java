package com.orosales.microservices.driverlicenceservicequery.dto;

public class FilterLicenceDTO {
    private String status;
    private String typeLicence;
    private String numLicence;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeLicence() {
        return typeLicence;
    }

    public void setTypeLicence(String typeLicence) {
        this.typeLicence = typeLicence;
    }

    public String getNumLicence() {
        return numLicence;
    }

    public void setNumLicence(String numLicence) {
        this.numLicence = numLicence;
    }
}
