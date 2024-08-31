package com.example.demo.domain.repository.Owner;

import com.example.demo.domain.entity.owner.Owner;
import com.example.demo.domain.entity.user.User;
import com.example.demo.infrastructure.web.projection.interfaceBased.OwnerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Se encargan de encapsular la lógica de acceso a datos
// proporcionar métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
// y consultas personalizadas sobre las entidades de la aplicación..
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    // MRtodo para verificar la existencia de un usuario
    boolean existsByUserId(User userId);

    @Query("SELECT o FROM Owner o")
    List<OwnerProjection> findAllProjectedBy();

    @Query("SELECT o FROM Owner o LEFT JOIN FETCH o.userId WHERE o.id = :id")
    Optional<OwnerProjection> findProjectedById(@Param("id") Integer id);
}
