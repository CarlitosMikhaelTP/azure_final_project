package com.example.demo.infrastructure.web.projection.interfaceBased;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
@JsonInclude(JsonInclude.Include.NON_NULL)

public interface LocationOwnerProjection {

    Integer getId();

    @Value("#{target.ownerId.id}")
    Integer getOwnerId();

    @Value("#{target.ownerId.userId.lastName}")
    String getOwnerLastName();

    @Value("#{target.ownerId.userId.name}")
    String getOwnerName();

    BigDecimal getLatitude();

    BigDecimal getLenght(); // Cambiado de getLenght a getLongitude

    Short getState();

}
