package com.example.demo.infrastructure.web.projection.interfaceBased;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface RatingWalkerProjection {

    Integer getId();

    @Value("#{target.walkerId.id}")
    Integer getWalkerId();

    @Value("#{target.walkId.id}")
    Integer getWalkId();

    Integer getRating();
}
