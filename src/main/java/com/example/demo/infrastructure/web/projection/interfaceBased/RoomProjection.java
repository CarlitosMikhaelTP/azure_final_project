package com.example.demo.infrastructure.web.projection.interfaceBased;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)

public interface RoomProjection {

    Integer getId();

    @Value("#{target.typeRoom.id}")
    Integer getTypeRoom();

    @Value("#{target.booking.id}")
    Integer getBooking();
    @JsonFormat(pattern = "yyyy-MM-dd")
    Timestamp getDate();

    Short getState();
}
