package com.example.demo.infrastructure.web.projection.interfaceBased;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalTime;
@JsonInclude(JsonInclude.Include.NON_NULL)

public interface BookingProjection {

    Integer getId();

    @Value("#{target.ownerId.id}")
    Integer getOwnerId();

    @Value("#{target.walkerId.id}")
    Integer getWalkerId();

    Double getCost();

    @JsonFormat(pattern = "yyyy-MM-dd")
    Timestamp getDate();

    @JsonFormat(pattern = "HH:mm:ss")
    LocalTime getDuration();

    String getComment();

    String getPlaceWalk();

    String getMeetingPoint();

    Short getState();

    Timestamp getCreatedAt();

}
