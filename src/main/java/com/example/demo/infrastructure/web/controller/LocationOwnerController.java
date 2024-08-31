package com.example.demo.infrastructure.web.controller;

import com.example.demo.application.service.OwnerService.LocationOwnerService;
import com.example.demo.infrastructure.web.projection.classBased.LocationOwnerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.LocationOwnerProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/LocationOwner")
@RequiredArgsConstructor
public class LocationOwnerController {

    private final LocationOwnerService locationOwnerService;

    // Endpoint para registrar Locación de Walker
    @PostMapping("/register")
    public ResponseEntity<LocationOwnerDTO> registerLocationOwner(@RequestBody LocationOwnerDTO locationOwnerDTO){
        LocationOwnerDTO registeredOwnerLocation = locationOwnerService.registerLocationOwnerService(locationOwnerDTO);
        return ResponseEntity.ok(registeredOwnerLocation);
    }

    // Endpoint para editar Locación de Walker
    @PutMapping("/edit/{id}")
    public ResponseEntity<LocationOwnerDTO> editLocationOwner(
            @PathVariable("id") Integer id,
            @RequestBody LocationOwnerDTO locationOwnerDTO
    ){
        LocationOwnerDTO updatedOwnerLocation = locationOwnerService.editLocationOwnerService(id, locationOwnerDTO);
        return ResponseEntity.ok(updatedOwnerLocation);
    }

    // Endpoint para listar todas las locaciones de los walker usando proyecciones
    @GetMapping("/findAllLocationOwners")
    public List<LocationOwnerProjection> findAllLocationOwners(){
        return locationOwnerService.showAllLocationsOwnersService();
    }

    // Endpoint para listar una locación de paseador por su ID
    @GetMapping("/findLocationOwner/{id}")
    public Optional<LocationOwnerProjection> findLocationOwnerById(@PathVariable Integer id){
        return locationOwnerService.findLocationOwnerByIdService(id);
    }

    // Endpoint para eliminar una locación de paseador por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLocationOwnerById(@PathVariable("id") Integer id){
        boolean deleted = locationOwnerService.deleteLocationOwnerByIdService(id);

        if (deleted) {
            return ResponseEntity.ok("Owner's Location were deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location Owner was not found for deleting");
        }

    }
}
