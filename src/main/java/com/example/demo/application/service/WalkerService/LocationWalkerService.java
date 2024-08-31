package com.example.demo.application.service.WalkerService;

import com.example.demo.infrastructure.web.projection.classBased.LocationWalkerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.LocationWalkerProjection;

import java.util.List;
import java.util.Optional;

public interface LocationWalkerService {

    // Definiendo interfaz para realizar el registro de Locaciones de Walker
    LocationWalkerDTO registerLocationWalkerService(LocationWalkerDTO locationWalkerDTO);

    // Definiendo interfaz para realizar la edición de Locaciones de Walker
    LocationWalkerDTO editLocationWalkerService(Integer id, LocationWalkerDTO locationWalkerDTO);

    // Definiendo Interfaz para mostrar las locaciones de walker registrados
    List<LocationWalkerProjection> showAllWalkersService();

    // Definiendo interfaz para mostrar una locacion de paseador por su ID
    Optional<LocationWalkerProjection> findLocationWalkerByIdService(Integer id);

    // Definiendo interfaz para eliminar una locación de paseador por su ID
    boolean deleteLocationWalkerByIdService(Integer id);
}
