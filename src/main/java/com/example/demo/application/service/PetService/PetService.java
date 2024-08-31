package com.example.demo.application.service.PetService;

import com.example.demo.infrastructure.web.projection.classBased.PetDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.PetProjection;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PetService {

    // DEFINIENDO INTERFAZ para realizar el registro de Pet
    PetDTO registerPetService(PetDTO petDTO);

    void updateFotoPetService(Integer id, MultipartFile foto) throws Exception;

    // DEFINIENDO INTERFAZ para realizar la edición de Pet
    PetDTO editPetService(Integer id, PetDTO petDTO);

    // DEFINIENDO INTERFAZ para mostrar a las mascotas registrados
    List<PetProjection> showAllPetService();

    // DEFINIENDO INTERFAZ para mostrar a una mascota por su ID
    Optional<PetProjection> showAllPetService(Integer id);

    // DEFINIENDO INTERFAZ para eliminar una mascota por su ID
    boolean deletePetById(Integer id);
}
