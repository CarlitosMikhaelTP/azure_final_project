package com.example.demo.domain.repository.Walker;

import com.example.demo.domain.entity.walk.Walk;
import com.example.demo.domain.entity.walker.LocationWalker;
import com.example.demo.domain.entity.walker.RatingWalker;
import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.infrastructure.web.projection.classBased.RatingWalkerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.LocationWalkerProjection;
import com.example.demo.infrastructure.web.projection.interfaceBased.RatingWalkerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingWalkerRepository extends JpaRepository<RatingWalker, Integer> {

    // Método para encontrar walker únicos para cada locación
    boolean existsByWalkerId(Walker walker);

    boolean existsByWalkId(Walk walk);
    //@Query("SELECT l FROM LocationWalker l LEFT JOIN FETCH l.walkerId w LEFT JOIN FETCH w.userId")
    List<RatingWalkerProjection> findAllProjectedBy();
    //@Query("SELECT l FROM LocationWalker l LEFT JOIN FETCH l.walkerId w LEFT JOIN FETCH w.userId WHERE l.id = :id")
    Optional<RatingWalkerProjection> findProjectedById(@Param("id") Integer id);
    Optional<RatingWalker> findById(Integer id);

    List<RatingWalker> findByWalkerId(Walker walker);


}
