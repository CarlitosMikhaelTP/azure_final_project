package com.example.demo.domain.repository.Owner;

import com.example.demo.domain.entity.owner.LocationOwner;
import com.example.demo.domain.entity.owner.Owner;
import com.example.demo.infrastructure.web.projection.interfaceBased.LocationOwnerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Implementación de métodos que vienen por defecto con la clase padre JpaRepository o mi propia
// implementación de estos métodos que me permiten mostrar información o eliminar de manera general o
// muy personalizada de la bd haciendo uso de proyecciones similar a DTO
@Repository
public interface LocationOwnerRepository extends JpaRepository<LocationOwner, Integer> {

    // Método para encontrar walker únicos para cada locación
    boolean existsByOwnerId(Owner owner);

    @Query("SELECT l FROM LocationOwner l LEFT JOIN FETCH l.ownerId o LEFT JOIN FETCH o.userId")
    List<LocationOwnerProjection> findAllProjectedBy();

    @Query("SELECT l FROM LocationOwner l LEFT JOIN FETCH l.ownerId o LEFT JOIN FETCH o.userId WHERE l.id = :id")
    Optional<LocationOwnerProjection> findProjectedById(@Param("id") Integer id);

    Optional<LocationOwner> findById(Integer id);
}
