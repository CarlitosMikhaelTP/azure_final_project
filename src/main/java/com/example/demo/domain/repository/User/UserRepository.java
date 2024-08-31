package com.example.demo.domain.repository.User;

import com.example.demo.domain.entity.booking.Booking;
import com.example.demo.domain.entity.user.User;
import com.example.demo.infrastructure.web.projection.interfaceBased.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

        // Método para buscar un usuario por su email
        // Carga explícitament el tipo de usuario
        @EntityGraph(attributePaths = "typeUserId")
        Optional<User> findUserByEmail(String email);

        // Método para mostrar el id del tipo de usuario de un usuario
        @Query("SELECT u FROM User u LEFT JOIN FETCH u.typeUserId WHERE u.id = :id")
        User findUserWithTypeById(@Param("id") Integer id);

        List<UserProjection> findAllProjectedBy();

        Optional<UserProjection> findProjectedById(Integer id);

        boolean existsById(User user);

}
