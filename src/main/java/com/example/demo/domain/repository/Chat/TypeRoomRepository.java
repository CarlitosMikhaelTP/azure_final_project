package com.example.demo.domain.repository.Chat;

import com.example.demo.domain.entity.chat.TypeMessage;
import com.example.demo.domain.entity.chat.TypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRoomRepository extends JpaRepository<TypeRoom, Integer> {

}
