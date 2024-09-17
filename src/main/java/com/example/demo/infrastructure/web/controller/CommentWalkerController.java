package com.example.demo.infrastructure.web.controller;

import com.example.demo.application.service.BookingService.BookingService;
import com.example.demo.application.service.CommentWalkerService.CommentWalkerService;
import com.example.demo.infrastructure.web.projection.classBased.BookingDTO;
import com.example.demo.infrastructure.web.projection.classBased.CommentWalkerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.BookingProjection;
import com.example.demo.infrastructure.web.projection.interfaceBased.CommentWalkerProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/commentWalker")
@RequiredArgsConstructor
public class CommentWalkerController {

    private final CommentWalkerService commentWalkerService;

    // Endpoint para registrar un comentario al paseador
    @PostMapping("/register")
    public ResponseEntity<CommentWalkerDTO> registerCommentWalker(@RequestBody CommentWalkerDTO commentWalkerDTO){
        CommentWalkerDTO registeredCommentWalker = commentWalkerService.registerCommentWalkerService(commentWalkerDTO);
        return ResponseEntity.ok(registeredCommentWalker);
    }

    // Endpoint para editar un comentario a los paseadores
    @PutMapping("/edit/{id}")
    public ResponseEntity<CommentWalkerDTO> editCommentWalkerService(
            @PathVariable("id") Integer id,
            @RequestBody CommentWalkerDTO commentWalkerDTO
    ){
        CommentWalkerDTO updatedCommentWalker = commentWalkerService.editCommentWalkerService(id, commentWalkerDTO);
        return ResponseEntity.ok(updatedCommentWalker);
    }

    // Endpoint para listar a todos los comentarios
    @GetMapping("/findAllCommentWalker")
    public List<CommentWalkerProjection> findAllCommentWalker(){
        return commentWalkerService.showAllCommentWalkerService();
    }

    // Endpoint para listar una reserva por un ID
    @GetMapping("/findCommentWalkerById/{id}")
    public Optional<CommentWalkerProjection> findCommentWalkerById(@PathVariable Integer id){
        return commentWalkerService.showCommentWalkerByIdService(id);
    }

    // Endpoint para listar todos los comentarios de un paseador
    @GetMapping("/showCommentsWalker/{walkerId}")
    public ResponseEntity<List<CommentWalkerProjection>> getAllCommentsByWalkerId(@PathVariable Integer walkerId) {
        List<CommentWalkerProjection> comments = commentWalkerService.showAllCommentsByWalkerIdService(walkerId);
        return ResponseEntity.ok(comments);
    }

    // Endpoint para eliminar una reserva por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCommentWalkerById(@PathVariable("id") Integer id){
        boolean deleted = commentWalkerService.deleteCommentById(id);

        if (deleted){
            return ResponseEntity.ok("The Walker´s comment was deleted successfull");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Was not possible delete the Walker´s comment");
        }
    }

}
