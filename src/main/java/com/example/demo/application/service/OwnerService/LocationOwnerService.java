package com.example.demo.application.service.OwnerService;

import com.example.demo.infrastructure.web.projection.classBased.LocationOwnerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.LocationOwnerProjection;
import java.util.List;
import java.util.Optional;

public interface LocationOwnerService {

    // Definiendo método para realizar el registro de Locaciones de Owner
    LocationOwnerDTO registerLocationOwnerService(LocationOwnerDTO locationOwnerDTO);

    // Definiendo interfaz para realizar la edición de Locaciones de Owner
    LocationOwnerDTO editLocationOwnerService(Integer id, LocationOwnerDTO locationOwnerDTO);

    // Definiendo Interfaz para mostrar las locaciones de owner registrados
    List<LocationOwnerProjection> showAllLocationsOwnersService();

    // Definiendo interfaz para mostrar una locacion de propietario por su ID
    Optional<LocationOwnerProjection> findLocationOwnerByIdService(Integer id);

    // Definiendo interfaz para eliminar una locación de propietario por su ID
    boolean deleteLocationOwnerByIdService(Integer id);

}
