package com.example.demo.domain.repository.Pet;

import com.example.demo.domain.entity.pet.BadHabits;
import com.example.demo.domain.entity.pet.TypePet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadHabitsRepository extends JpaRepository<BadHabits, Integer> {
}
