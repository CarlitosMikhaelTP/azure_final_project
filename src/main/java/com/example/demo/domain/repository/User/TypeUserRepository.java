package com.example.demo.domain.repository.User;

import com.example.demo.domain.entity.user.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeUserRepository extends JpaRepository<TypeUser, Integer> {

}
