package com.example.demo.infrastructure.web.projection.interfaceBased;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
@JsonInclude(JsonInclude.Include.NON_NULL)

public interface OwnerProjection {

    Integer getId();

    @Value("#{target.userId.id}")
    Integer getUserId();

    @Value("#{target.userId.name}")
    String getName();

    @Value("#{target.userId.lastName}")
    String getLastName();

    Integer getCalification();

    String getFoto();
    String getComment();
    String getWalkingPreferences();
    Double getMoney(); // Cambiado de BigDecimal a Double para coincidir con la entidad
    Boolean getAvailability();
    String getLocation(); // Corregido de getlocation() a getLocation()

    @Value("#{target.locationOwner != null ? target.locationOwner.latitude : null}")
    Double getLatitude();

    @Value("#{target.locationOwner != null ? target.locationOwner.lenght : null}")
    Double getLongitude();
}
