package com.example.demo.domain.repository.Walker;

import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.domain.entity.user.User;
import com.example.demo.infrastructure.web.projection.interfaceBased.WalkerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalkerRepository extends JpaRepository<Walker, Integer> {

    boolean existsByUserId (User userId);

    // Método para obtener todos los walker con la proyección
    @Query("SELECT o FROM Walker o")
    List<WalkerProjection> findAllProjectedBy();

    // Método para obtener un paseador por su ID
    @Query("SELECT w FROM Walker w LEFT JOIN FETCH w.userId LEFT JOIN FETCH w.categoryId LEFT JOIN FETCH w.locationWalker WHERE w.id = :id")
    Optional<WalkerProjection> findProjectedById(@Param("id") Integer id);
}
