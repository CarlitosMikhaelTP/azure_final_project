package com.example.demo.infrastructure.web.controller;

import com.example.demo.application.service.PetService.PetService;
import com.example.demo.infrastructure.web.projection.classBased.PetDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.PetProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pet")
@RequiredArgsConstructor
public class PetController {

    // Inicializamos las clases para trabajar con este constructor con "final"
    private final PetService petService;

    // Endpoint para registrar Pet
    @PostMapping("/register")
    public ResponseEntity<PetDTO> registerPet(@RequestBody PetDTO petDTO){
        PetDTO registeredPet = petService.registerPetService(petDTO);
        return ResponseEntity.ok(registeredPet);
    }

    // Endpoint para editar Pet
    @PutMapping("/edit/{id}")
    public ResponseEntity<PetDTO> ediPet(
            @PathVariable("id") Integer id,
            @RequestBody PetDTO petDTO
    ) {
        PetDTO updatedPet = petService.editPetService(id, petDTO);
        return ResponseEntity.ok(updatedPet);
    }

    // Endpoint para listar a todos las mascotas usando proyecciones
    @GetMapping("/findAllPet")
    public List<PetProjection> findAllPet(){
        return petService.showAllPetService(); // Obtiene todos las mascotas con la proyección
    }

    // Endpoint para listar una mascota por su ID
    @GetMapping("/findPetById/{id}")
    public Optional<PetProjection> findPetById(@PathVariable Integer id){
        return petService.showAllPetService(id);
    }

    // Endpoint para eliminar una mascota por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePetById(@PathVariable("id") Integer id) {
        boolean deleted = petService.deletePetById(id);

        if (deleted) {
            return ResponseEntity.ok("Pet was deleted successfull");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Was not possible delete the pet");
        }
    }

    @PostMapping("/{id}/foto")
    public ResponseEntity<String> sendFotoPet(
            @PathVariable Integer id,
            @RequestParam("foto") MultipartFile foto) {
        try {
            petService.updateFotoPetService(id, foto);
            return ResponseEntity.ok("Foto de la mascota actualizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la foto del mascota: " + e.getMessage());
        }
    }
}
