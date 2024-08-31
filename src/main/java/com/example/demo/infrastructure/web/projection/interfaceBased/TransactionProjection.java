package com.example.demo.infrastructure.web.projection.interfaceBased;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface TransactionProjection {

    Integer getId();

    @Value("#{target.walker.id}")
    Integer getWalkerId();

    @Value("#{target.owner.id}")
    Integer getOwnerId();

    @Value("#{target.typeTransactionId.name}")
    String getTypeTransactionName();

    @Value("#{target.stateTransactionId.name}")
    String getStateTransactionName();

    Double getCost();

    Short getState();

    Timestamp getCreatedAt();

    // Puedes añadir más campos según sea necesario

}
