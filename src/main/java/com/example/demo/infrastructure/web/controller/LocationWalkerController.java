package com.example.demo.infrastructure.web.controller;

import com.example.demo.application.service.WalkerService.LocationWalkerService;
import com.example.demo.infrastructure.web.projection.classBased.LocationWalkerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.LocationWalkerProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/LocationWalker")
@RequiredArgsConstructor
public class LocationWalkerController {

    private final LocationWalkerService locationWalkerService;

    // Endpoint para registrar Locación de Walker
    @PostMapping("/register")
    public ResponseEntity<LocationWalkerDTO> registerLocationWalker(@RequestBody LocationWalkerDTO locationWalkerDTO){
        LocationWalkerDTO registeredWalkerLocation = locationWalkerService.registerLocationWalkerService(locationWalkerDTO);
        return ResponseEntity.ok(registeredWalkerLocation);
    }

    // Endpoint para editar Locación de Walker
    @PutMapping("/edit/{id}")
    public ResponseEntity<LocationWalkerDTO> editLocationWalker(
            @PathVariable("id") Integer id,
            @RequestBody LocationWalkerDTO locationWalkerDTO
    ){
        LocationWalkerDTO updatedWalkerLocation = locationWalkerService.editLocationWalkerService(id, locationWalkerDTO);
        return ResponseEntity.ok(updatedWalkerLocation);
    }

    // Endpoint para listar todas las locaciones de los walker usando proyecciones
    @GetMapping("/findAllLocationWalkers")
    public List<LocationWalkerProjection> showAllWalkers(){
        return locationWalkerService.showAllWalkersService();
    }

    // Endpoint para listar una locación de paseador por su ID
    @GetMapping("/findLocationWalker/{id}")
    public Optional<LocationWalkerProjection> findLocationWalkerById(@PathVariable Integer id){
        return locationWalkerService.findLocationWalkerByIdService(id);
    }

    // Endpoint para eliminar una locación de paseador por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLocationWalkerById(@PathVariable("id") Integer id){
        boolean deleted = locationWalkerService.deleteLocationWalkerByIdService(id);

        if (deleted) {
            return ResponseEntity.ok("Walker's Location was deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location Walker was not found for deleting");
        }

    }

}
