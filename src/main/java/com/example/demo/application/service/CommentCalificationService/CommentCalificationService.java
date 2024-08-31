package com.example.demo.application.service.CommentCalificationService;

import com.example.demo.infrastructure.web.projection.classBased.CommentCalificationDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.CommentCalificationProjection;

import java.util.List;
import java.util.Optional;

public interface CommentCalificationService {

    // Definiendo interfaz para realizar el registro de Calificaciones y comentarios
    CommentCalificationDTO registerCommentCalificationService(CommentCalificationDTO commentCalificationDTO);

    // Definimos interfaz para realizar la edición de calificaciones y comentarios
    CommentCalificationDTO editCommentCalificationService(Integer id, CommentCalificationDTO commentCalificationDTO);

    // Definiendo interfaz para mostrar las calificaciones y comentarios registrados
    List<CommentCalificationProjection> showAllCommentCalificationService();

    // Definimos interfaz para mostrar un paseo por su ID
    Optional<CommentCalificationProjection> showCommentCalificationByIdService(Integer id);

    // Definiendo interfaz para eliminar un paseo por su ID
    boolean deleteCommentCalificationById(Integer id);

}
