package com.orosales.microservices.auditservice.repository;

import com.orosales.microservices.commonmodels.model.entity.AuditInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuditRepo extends JpaRepository<AuditInfo, Integer> {
}
