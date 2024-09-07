package com.example.demo.infrastructure.web.projection.interfaceBased;

import com.example.demo.domain.entity.chat.Room;
import com.example.demo.domain.entity.chat.TypeMessage;
import com.example.demo.domain.entity.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)

public interface ChatProjection {

    Integer getId();

    @Value("#{target.room.id}")
    Integer getRoom();

    @Value("#{target.user.id}")
    Integer getUser();

    @Value("#{target.typeMessage.id}")
    Integer getTypeMessage();

    String getMessage();

    @JsonFormat(pattern = "yyyy-MM-dd")
    Timestamp getDate();

    Short getState();
    String getUrl();
}
