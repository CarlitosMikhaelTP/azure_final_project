package com.example.demo.domain.repository.Pet;

import com.example.demo.domain.entity.pet.Pet;
import com.example.demo.domain.entity.owner.Owner;
import com.example.demo.infrastructure.web.projection.interfaceBased.PetProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    boolean existsByOwner(Owner owner);

    @Query("SELECT p FROM Pet p LEFT JOIN FETCH p.typePetId LEFT JOIN FETCH p.owner")
    List<PetProjection> findAllProjectedBy();

    @Query("SELECT p FROM Pet p LEFT JOIN FETCH p.typePetId LEFT JOIN FETCH p.owner WHERE p.id = :id")
    Optional<PetProjection> findProjectedById(@Param("id") Integer id);
}
