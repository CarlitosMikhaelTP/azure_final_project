package com.example.demo.infrastructure.web.controller;

import com.example.demo.application.service.BookingService.BookingService;
import com.example.demo.application.service.ChatService.ChatService;
import com.example.demo.domain.entity.chat.Chat;
import com.example.demo.infrastructure.web.projection.classBased.BookingDTO;
import com.example.demo.infrastructure.web.projection.classBased.ChatDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.BookingProjection;
import com.example.demo.infrastructure.web.projection.interfaceBased.ChatProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    // Endpoint para registrar Chat
    @PostMapping("/register")
    public ResponseEntity<ChatDTO> registerChat(@RequestBody ChatDTO chatDTO){
        ChatDTO registeredChat = chatService.registerChatService(chatDTO);
        return ResponseEntity.ok(registeredChat);
    }

    // Endpoint para editar el chat
    @PutMapping("/edit/{id}")
    public ResponseEntity<ChatDTO> editChatService(
            @PathVariable("id") Integer id,
            @RequestBody ChatDTO chatDTO
    ){
        ChatDTO updatedChat = chatService.editChatService(id, chatDTO);
        return ResponseEntity.ok(updatedChat);
    }

    // Endpoint para listar a todos los chats usando proyecciones
    @GetMapping("/findAllChat")
    public List<ChatProjection> findAllChat(){
        return chatService.showAllChatService(); // Obtiene todos los chats con la proyección
    }

    // Endpoint para listar un chat por un ID
    @GetMapping("/findChatById/{id}")
    public Optional<ChatProjection> findChatById(@PathVariable Integer id){
        return chatService.showChatByIdService(id);
    }

    // Endpoint para eliminar un chat por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteChatById(@PathVariable("id") Integer id){
        boolean deleted = chatService.deleteChatById(id);

        if (deleted){
            return ResponseEntity.ok("The chat was deleted successfull");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Was not possible delete the Chat");
        }
    }

}
