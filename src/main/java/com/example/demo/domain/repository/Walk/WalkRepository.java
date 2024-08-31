package com.example.demo.domain.repository.Walk;

import com.example.demo.domain.entity.booking.Booking;
import com.example.demo.domain.entity.walk.Walk;
import com.example.demo.infrastructure.web.projection.interfaceBased.WalkProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalkRepository extends JpaRepository<Walk, Integer> {

    // Para verificar que solo haya una reserva relacionada
   // boolean existsByBooking(Booking booking);

    // Método para obtener todos los walk por proyección
    @Query("SELECT w FROM Walk w LEFT JOIN FETCH w.bookingId")
    List<WalkProjection> findAllProjectedBy();

    @Query("SELECT w FROM Walk w LEFT JOIN FETCH w.bookingId WHERE w.id = :id")
    Optional<WalkProjection> findProjectedById(@Param("id") Integer id);

    boolean existsByBookingId(Booking booking);
}
