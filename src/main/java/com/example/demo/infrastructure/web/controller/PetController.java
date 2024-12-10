package com.example.demo.infrastructure.web.controller;

import com.example.demo.application.exceptions.WalkerExceptions.NotFound.WalkerNotFoundException;
import com.example.demo.application.service.PetService.PetService;
import com.example.demo.domain.entity.pet.Pet;
import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.domain.repository.Pet.PetRepository;
import com.example.demo.infrastructure.web.projection.classBased.PetDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.PetProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pet")
@RequiredArgsConstructor
public class PetController {

    // Inicializamos las clases para trabajar con este constructor con "final"
    private final PetService petService;
    private final PetRepository petRepository;

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

    @GetMapping("/foto/{id}")
    public ResponseEntity<Resource> obtenerFotoPet(@PathVariable Integer id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new WalkerNotFoundException("Walker not found"));

        String rutaFoto = pet.getFoto();

        // Cargar el archivo de la ruta
        Path filePath = Paths.get(rutaFoto);
        Resource resource = null;

        try {
            resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Cambiar el tipo MIME según el tipo de archivo
                        .body(resource);
            } else {
                throw new FileNotFoundException("Was not possible find or read the pet's image");
            }
        } catch (MalformedURLException | FileNotFoundException e) {
            // Manejo de excepciones si el archivo no se encuentra o no se puede leer
            // Devolver un error o un recurso vacío en la respuesta
            return ResponseEntity.notFound().build();
        }
    }
}
