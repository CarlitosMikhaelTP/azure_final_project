package com.example.demo.infrastructure.web.projection.interfaceBased;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;
@JsonInclude(JsonInclude.Include.NON_NULL)

public interface CommentCalificationProjection {

    Integer getId();

    @Value("#{target.walkId.id}")
    Integer getWalkId();

    @Value("#{target.walkId.start}")
    Timestamp getWalkDate();

    @Value("#{target.walkId.cost}")
    Double getWalkCost();

    String getComment();

    Integer getCalification();

    Short getState();

    Timestamp getCreatedAt();

    @Value("#{target.createdBy.id}")
    Integer getCreatedById();

}
