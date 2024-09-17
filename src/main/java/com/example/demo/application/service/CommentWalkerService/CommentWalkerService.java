package com.example.demo.application.service.CommentWalkerService;

import com.example.demo.infrastructure.web.projection.classBased.CommentCalificationDTO;
import com.example.demo.infrastructure.web.projection.classBased.CommentWalkerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.CommentCalificationProjection;
import com.example.demo.infrastructure.web.projection.interfaceBased.CommentWalkerProjection;

import java.util.List;
import java.util.Optional;

public interface CommentWalkerService {

    // Definiendo interfaz para realizar el registro de comentarios
    CommentWalkerDTO registerCommentWalkerService(CommentWalkerDTO commentWalkerDTO);

    // Definimos interfaz para realizar la edición de  comentarios
    CommentWalkerDTO editCommentWalkerService(Integer id, CommentWalkerDTO commentWalkerDTO);

    // Definiendo interfaz para mostrar los comentarios registrados
    List<CommentWalkerProjection> showAllCommentWalkerService();

    // Definimos interfaz para mostrar un comentario por su ID
    Optional<CommentWalkerProjection> showCommentWalkerByIdService(Integer id);

    // Definiendo interfaz para eliminar un paseo por su ID
    boolean deleteCommentById(Integer id);

    // Definiendo interfaz para mostrar todos los coemntarios de un paseador
    List<CommentWalkerProjection> showAllCommentsByWalkerIdService(Integer walkerId);

}
