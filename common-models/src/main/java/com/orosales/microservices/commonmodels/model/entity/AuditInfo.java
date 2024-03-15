package com.orosales.microservices.commonmodels.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuditInfo extends GenericEntity<AuditInfo> implements Serializable {

    @Serial
    private static final long serialVersionUID = 129348939L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAuditInfo;

    @Column
    private Long currentTimestampAction;

    @Column
    private String appCallerName;

    @Column
    private String opnNumber;

    @Column
    private String message;

    @Column
    private String statusCode;

    @Column
    private String exception;

    @Column
    private String exceptionDetails;


}