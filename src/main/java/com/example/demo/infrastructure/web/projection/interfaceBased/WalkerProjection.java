package com.example.demo.infrastructure.web.projection.interfaceBased;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface WalkerProjection {
    // Anotación para llamar directamente a la propiedad de
    // otro campo de la propiedad de categoría en este caso
    // del nombre
    Integer getId();

    @Value("#{target.userId.id}")
    Integer getUserId();

    @Value("#{target.userId.name}")
    String getName();

    @Value("#{target.userId.lastName}")
    String getLastName();

    @Value("#{target.userId.dni}")
    String getDni();

    @Value("#{target.userId.email}")
    String getEmail();

    @Value("#{target.userId.celphone}")
    String getCelphone();

    @Value("#{target.categoryId.nameCategory}")
    String getCategoryName();

    Integer getCalification();

    Integer getNumber_walk();

    String getFoto();

    String getDescription();

    String getExperience();

    String getLocation();

    Double getCost();

    Double getMoney();

    Boolean getAvailability();

    Short getState();

    @Value("#{target.locationWalker != null ? target.locationWalker.latitude : null}")
    Double getLatitude();

    @Value("#{target.locationWalker != null ? target.locationWalker.lenght : null}")
    Double getLongitude();

}
