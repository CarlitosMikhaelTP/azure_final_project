package com.example.demo.infrastructure.web.controller;

import com.example.demo.application.service.ChatService.ChatService;
import com.example.demo.application.service.ChatService.RoomService;
import com.example.demo.domain.entity.chat.Room;
import com.example.demo.infrastructure.web.projection.classBased.ChatDTO;
import com.example.demo.infrastructure.web.projection.classBased.RoomDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.ChatProjection;
import com.example.demo.infrastructure.web.projection.interfaceBased.RoomProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    // Endpoint para registrar una sala
    @PostMapping("/register")
    public ResponseEntity<RoomDTO> registerChat(@RequestBody RoomDTO roomDTO){
        RoomDTO registeredRoom = roomService.registerRoomService(roomDTO);
        return ResponseEntity.ok(registeredRoom);
    }

    // Endpoint para editar el chat
    @PutMapping("/edit/{id}")
    public ResponseEntity<RoomDTO> editRoomService(
            @PathVariable("id") Integer id,
            @RequestBody RoomDTO roomDTO
    ){
        RoomDTO updatedRoom = roomService.editRoomService(id, roomDTO);
        return ResponseEntity.ok(updatedRoom);
    }

    // Endpoint para listar a todos los chats usando proyecciones
    @GetMapping("/findAllRoom")
    public List<RoomProjection> findAllChat(){
        return roomService.showAllRoomService(); // Obtiene todos las salas con la proyección
    }

    // Endpoint para listar un chat por un ID
    @GetMapping("/findRoomById/{id}")
    public Optional<RoomProjection> findRoomById(@PathVariable Integer id){
        return roomService.showRoomByIdService(id);
    }

    // Endpoint para eliminar un chat por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoomById(@PathVariable("id") Integer id){
        boolean deleted = roomService.deleteRoomById(id);

        if (deleted){
            return ResponseEntity.ok("The room was deleted successfull");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Was not possible delete the Room");
        }
    }

}
