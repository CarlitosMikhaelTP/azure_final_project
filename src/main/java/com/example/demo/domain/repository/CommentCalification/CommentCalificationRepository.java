package com.example.demo.domain.repository.CommentCalification;

import com.example.demo.domain.entity.commentCalification.CommentCalification;
import com.example.demo.infrastructure.web.projection.interfaceBased.CommentCalificationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentCalificationRepository extends JpaRepository<CommentCalification, Integer> {

    @Query("SELECT c FROM CommentCalification c LEFT JOIN FETCH c.walkId")
    List<CommentCalificationProjection> findAllProjectedBy();

    @Query("SELECT c FROM CommentCalification c LEFT JOIN FETCH c.walkId WHERE c.id = :id")
    Optional<CommentCalificationProjection> findProjectedById(@Param("id") Integer id);
}
