package com.example.demo.application.service.OwnerService;

import com.example.demo.infrastructure.web.projection.classBased.OwnerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.OwnerProjection;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface OwnerService {

    // Definiendo interfaz para realizar el registro de Owner
    OwnerDTO registerOwnerService(OwnerDTO ownerDTO);

    // Definiendo interfaz para realizar la edición de Owner
    OwnerDTO editOwnerService(Integer id, OwnerDTO ownerDTO);

    // Definiendo interfaz para mostrar a los owner registrados
    //List<OwnerDTO> showAllOwnersService();

    // Definiendo Interfaz para mostrar a propietario por su ID
    Optional<OwnerProjection> findOwnerByIdService(Integer id);

    //Definiendo Interfaz para eliminar un propietario por su ID
    boolean deleteOwnerByIdService(Integer id);

    //Método para obtener toda la lista de los dueños de las mascotas
    List<OwnerProjection> getAvailableOwnersService();

    void updateFotoOwnerService(Integer id, MultipartFile foto) throws Exception;
}
