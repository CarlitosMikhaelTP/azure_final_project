package com.example.demo.domain.repository.Chat;

import com.example.demo.domain.entity.booking.Booking;
import com.example.demo.domain.entity.chat.Room;
import com.example.demo.domain.entity.chat.TypeMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeMessageRepository extends JpaRepository<TypeMessage, Integer> {

}
