package com.example.demo.application.service.ChatService;

import com.example.demo.infrastructure.web.projection.classBased.ChatDTO;
import com.example.demo.infrastructure.web.projection.classBased.RoomDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.ChatProjection;
import com.example.demo.infrastructure.web.projection.interfaceBased.RoomProjection;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    RoomDTO registerRoomService(RoomDTO roomDTO);

    RoomDTO editRoomService(Integer id, RoomDTO roomDTO);

    List<RoomProjection> showAllRoomService();

    Optional<RoomProjection> showRoomByIdService(Integer id);

    boolean deleteRoomById(Integer id);
}
