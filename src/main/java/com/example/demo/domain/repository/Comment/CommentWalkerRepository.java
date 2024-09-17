package com.example.demo.domain.repository.Comment;

import com.example.demo.domain.entity.comment.CommentWalker;
import com.example.demo.domain.entity.commentCalification.CommentCalification;
import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.infrastructure.web.projection.interfaceBased.CommentCalificationProjection;
import com.example.demo.infrastructure.web.projection.interfaceBased.CommentWalkerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentWalkerRepository extends JpaRepository<CommentWalker, Integer> {

    List<CommentWalkerProjection> findAllProjectedBy();

    Optional<CommentWalkerProjection> findProjectedById(
            @Param("id")
            Integer id);

    List<CommentWalkerProjection> findAllByWalkerId(Walker walker);
}
