package com.orosales.microservices.commonmodels.model.entity;



import jakarta.persistence.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Licence extends GenericEntity<AuditInfo> implements Serializable {

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
