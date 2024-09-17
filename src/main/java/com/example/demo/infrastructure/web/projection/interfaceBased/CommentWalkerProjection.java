package com.example.demo.infrastructure.web.projection.interfaceBased;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)

public interface CommentWalkerProjection {

    Integer getId();
    @Value("#{target.walkerId.id}")
    Integer getWalkerId();
    @Value("#{target.walkId.id}")
    Integer getWalkId();
    @Value("#{target.userId.id}")
    Integer getUserId();
    String getComment();
    @JsonFormat(pattern = "yyyy-MM-dd")
    Timestamp getDate();
    Short getState();
    Timestamp getCreatedAt();

}
