package com.example.demo.infrastructure.web.projection.interfaceBased;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface LocationWalkerProjection {

    Integer getId();

    @Value("#{target.walkerId.id}")
    Integer getWalkerId();

    @Value("#{target.walkerId.userId.lastName}")
    String getWalkerLastName();

    @Value("#{target.walkerId.userId.name}")
    String getWalkerName();

    BigDecimal getLatitude();

    BigDecimal getLenght();

    Short getState();
}
