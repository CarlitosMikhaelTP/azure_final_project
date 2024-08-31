package com.example.demo.infrastructure.web.controller;

import com.example.demo.application.exceptions.OwnerExceptions.NotFound.OwnerNotFoundException;
import com.example.demo.application.service.OwnerService.OwnerService;
import com.example.demo.domain.entity.owner.Owner;
import com.example.demo.domain.repository.Owner.OwnerRepository;
import com.example.demo.infrastructure.web.projection.classBased.OwnerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.OwnerProjection;
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
@RequestMapping("/api/v1/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerRepository ownerRepository;

    // Endpoint para registrar Owner
    @PostMapping("/register")
    public ResponseEntity<OwnerDTO> registerOwner(@RequestBody OwnerDTO ownerDTO){
        OwnerDTO registeredOwner = ownerService.registerOwnerService(ownerDTO);
        return ResponseEntity.ok(registeredOwner);
    }

    // Endpoint para editar Owner
    @PutMapping("/edit/{id}")
    public ResponseEntity<OwnerDTO> editOwner(
            @PathVariable("id") Integer id,
            @RequestBody OwnerDTO ownerDTO
    ){
        OwnerDTO updatedOwner = ownerService.editOwnerService(id, ownerDTO);
        return ResponseEntity.ok(updatedOwner);
    }

    // Endpoint para listar a todos los owner usando proyecciones
    @GetMapping("/findAllOwners")
    public List<OwnerProjection> getAvailableOwners() {
        return ownerService.getAvailableOwnersService();
    }

    // Endpoint para listar un propietario por un ID
    @GetMapping("/findOwnerById/{id}")
    public Optional<OwnerProjection> findOwnerById(@PathVariable Integer id){
        return ownerService.findOwnerByIdService(id);
    }

    // Endpoint para eliminar un propietario por un ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOwnerById(@PathVariable("id") Integer id){
        boolean deleted = ownerService.deleteOwnerByIdService(id);

        if (deleted){
            return ResponseEntity.ok("Owner was deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner was not found for deleting");
        }
    }

    // Controlador para subir la foto de un dueño de la mascota
    @PostMapping("/{id}/foto")
    public ResponseEntity<String> updateFotoOwner(
            @PathVariable Integer id,
            @RequestParam("foto") MultipartFile foto) {
        try {
            ownerService.updateFotoOwnerService(id, foto);
            return ResponseEntity.ok("Owner's Foto was updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There's an error during deleting's foto " + e.getMessage());
        }
    }

    @GetMapping("/foto/{id}")
    public ResponseEntity<Resource> obtenerFotoPropietario(@PathVariable Integer id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException("Owner was not found"));

        String rutaFoto = owner.getFoto();

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
                throw new FileNotFoundException("Was not possible found or read the owner's image");
            }
        } catch (MalformedURLException | FileNotFoundException e) {
            // Manejo de excepciones si el archivo no se encuentra o no se puede leer
            // Devolver un error o un recurso vacío en la respuesta
            return ResponseEntity.notFound().build();
        }
    }
}

