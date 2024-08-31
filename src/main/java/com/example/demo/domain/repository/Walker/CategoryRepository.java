package com.example.demo.domain.repository.Walker;

import com.example.demo.domain.entity.walker.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
