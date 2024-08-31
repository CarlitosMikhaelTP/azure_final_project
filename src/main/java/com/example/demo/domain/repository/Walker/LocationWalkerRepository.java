package com.example.demo.domain.repository.Walker;

import com.example.demo.domain.entity.walker.LocationWalker;
import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.infrastructure.web.projection.interfaceBased.LocationWalkerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationWalkerRepository extends JpaRepository<LocationWalker, Integer> {

    // Método para encontrar walker únicos para cada locación
    boolean existsByWalkerId(Walker walker);

    @Query("SELECT l FROM LocationWalker l LEFT JOIN FETCH l.walkerId w LEFT JOIN FETCH w.userId")
    List<LocationWalkerProjection> findAllProjectedBy();

    @Query("SELECT l FROM LocationWalker l LEFT JOIN FETCH l.walkerId w LEFT JOIN FETCH w.userId WHERE l.id = :id")
    Optional<LocationWalkerProjection> findProjectedById(@Param("id") Integer id);

    Optional<LocationWalker> findById(Integer id);
}

