package com.example.demo.application.service.WalkService;

import com.example.demo.infrastructure.web.projection.classBased.WalkDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.WalkProjection;

import java.util.List;
import java.util.Optional;

public interface WalkService {

    // Definiendo interfaz para realizar el registro de walk
    WalkDTO registerWalkService(WalkDTO walkDTO);

    // Definimos interfaz para realizar la edición de walk
    WalkDTO editWalkService(Integer id, WalkDTO walkDTO);

    // Definiendo interfaz para mostrar los walk registrados
    List<WalkProjection> showAllWalkService();

    // Definimos interfaz para mostrar un paseo por su ID
    Optional<WalkProjection> showWalkByIdService(Integer id);

    // Definiendo interfaz para eliminar un paseo por su ID
    boolean deleteWalkByIdService(Integer id);
}
