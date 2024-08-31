package com.example.demo.infrastructure.web.controller;

import com.example.demo.application.service.CommentCalificationService.CommentCalificationService;
import com.example.demo.infrastructure.web.projection.classBased.CommentCalificationDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.CommentCalificationProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/calificationComent")
@RequiredArgsConstructor
public class CommentCalificationController {

    private final CommentCalificationService commentCalificationService;

    // Endpoint para registrar la calificaciony comentario
    @PostMapping("/register")
    public ResponseEntity<CommentCalificationDTO> registerCommentCalilfication(@RequestBody CommentCalificationDTO commentCalificationDTO){
        CommentCalificationDTO registeredCommentCalification = commentCalificationService.registerCommentCalificationService(commentCalificationDTO);
        return ResponseEntity.ok(registeredCommentCalification);
    }

    // Endpoint para editar Walker
    @PutMapping("/edit/{id}")
    public ResponseEntity<CommentCalificationDTO> editCommentCalification(
            @PathVariable("id") Integer id,
            @RequestBody CommentCalificationDTO commentCalificationDTO
    ) {
        CommentCalificationDTO updatedCommentCalification = commentCalificationService.editCommentCalificationService(id, commentCalificationDTO);
        return ResponseEntity.ok(updatedCommentCalification);
    }

    // Endpoint para listar a todos los walker usando proyecciones
    @GetMapping("/findAllCommentCalification")
    public List<CommentCalificationProjection> findAllCommentCalification(){
        return commentCalificationService.showAllCommentCalificationService(); // Obtiene todos los walker con la proyección
    }

    // Endpoint para listar un paseador por su ID
    @GetMapping("/findCommentCalificationById/{id}")
    public Optional<CommentCalificationProjection> findCommentCalificationById(@PathVariable Integer id){
        return commentCalificationService.showCommentCalificationByIdService(id);
    }

    // Endpoint para eliminar una calificacion y comentario por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCommentCalificationById(@PathVariable("id") Integer id) {
        boolean deleted = commentCalificationService.deleteCommentCalificationById(id);

        if (deleted) {
            return ResponseEntity.ok("Comment and calification was deleted succesfull");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Was not possible delete the comment and calification !!");
        }
    }
}
