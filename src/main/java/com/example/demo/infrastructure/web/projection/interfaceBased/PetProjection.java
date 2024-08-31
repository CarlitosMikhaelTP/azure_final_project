package com.example.demo.infrastructure.web.projection.interfaceBased;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface PetProjection {

    Integer getId();

    String getName();

    String getRace();

    String getAge();

    String getNeeds();

    @Value("#{target.typePetId.name}")
    String getTypePetName();

    @Value("#{target.owner.userId.name}")
    String getOwnerName();

    String getFoto();

    String getWeight();

    Short getState();
}
