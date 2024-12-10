package com.example.demo.infrastructure.web.projection.interfaceBased;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface WalkProjection {


    Integer getId();

    @Value("#{target.bookingId.id}")
    Integer getBookingId();

    @Value("#{target.bookingId.ownerId.userId.name}")
    String getNameOwner();

    @Value("#{target.bookingId.walkerId.userId.name}")
    String getNameWalker();

    @JsonFormat(pattern = "yyy-MM-dd")
    Timestamp getStart();

    @JsonFormat(pattern = "HH:mm:ss")
    LocalTime getDuration();

    String getPlaceWalk();

    String getComment();

    //Integer getCalification();

    Double getCost();

    Short getState();
}
