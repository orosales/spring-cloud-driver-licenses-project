package com.orosales.microservices.driverlicenceservice.model;



import jakarta.persistence.*;

import java.time.LocalDate;


public class Licence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLicence;

    @Column(nullable = false, length = 15)
    private String numDocumentClient;
    @Column(nullable = false, length = 25)
    private String numLicence;
    @Column(nullable = false, length = 25)
    private String typeLicence;
    @Column
    private LocalDate dateIssue;
    @Column
    private LocalDate dateExpired;
    @Column(nullable = false, length = 15)
    private String status;

    @Column(nullable = false, length = 120)
    private String nameCustomer;

    public Integer getIdLicence() {
        return idLicence;
    }

    public void setIdLicence(Integer idLicence) {
        this.idLicence = idLicence;
    }

    public String getNumDocumentClient() {
        return numDocumentClient;
    }

    public void setNumDocumentClient(String numDocumentClient) {
        this.numDocumentClient = numDocumentClient;
    }

    public String getNumLicence() {
        return numLicence;
    }

    public void setNumLicence(String numLicence) {
        this.numLicence = numLicence;
    }

    public String getTypeLicence() {
        return typeLicence;
    }

    public void setTypeLicence(String typeLicence) {
        this.typeLicence = typeLicence;
    }

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



    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }
}
