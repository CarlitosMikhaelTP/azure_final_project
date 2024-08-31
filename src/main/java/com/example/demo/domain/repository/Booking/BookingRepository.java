package com.example.demo.domain.repository.Booking;

import com.example.demo.domain.entity.booking.Booking;
import com.example.demo.infrastructure.web.projection.interfaceBased.BookingProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT b FROM Booking b LEFT JOIN FETCH b.ownerId LEFT JOIN FETCH b.walkerId")
    List<BookingProjection> findAllProjectedBy();

    @Query("SELECT b FROM Booking b LEFT JOIN FETCH b.ownerId LEFT JOIN FETCH b.walkerId WHERE b.id = :id")
    Optional<BookingProjection> findProjectedById(@Param("id") Integer id);

}
