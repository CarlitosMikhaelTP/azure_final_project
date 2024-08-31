package com.example.demo.infrastructure.web.controller;

import com.example.demo.application.service.WalkService.WalkService;
import com.example.demo.infrastructure.web.projection.classBased.WalkDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.WalkProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/walk")
@RequiredArgsConstructor
public class WalkController {

    private final WalkService walkService;

    // Endpoint para registrar Walk
    @PostMapping("/register")
    public ResponseEntity<WalkDTO> registerWalk(@RequestBody WalkDTO walkDTO){
        WalkDTO registeredWalk = walkService.registerWalkService(walkDTO);
        return ResponseEntity.ok(registeredWalk);
    }

    // Endpoint para editar walk
    @PutMapping("/edit/{id}")
    public ResponseEntity<WalkDTO> editWalk(
            @PathVariable("id") Integer id,
            @RequestBody WalkDTO walkDTO
    ){
        WalkDTO updatedWalk = walkService.editWalkService(id, walkDTO);
        return ResponseEntity.ok(updatedWalk);
    }

    // Endpoint para listar a todos los walk usando proyecciones
    @GetMapping("/findAllWalk")
    public List<WalkProjection> findAllWalk(){
        return walkService.showAllWalkService(); // Obtiene todos los walk con la proyección
    }

    // Endpoint para listar un paseo por un ID
    @GetMapping("/findWalkById/{id}")
    public Optional<WalkProjection> findWalkById(@PathVariable Integer id){
        return walkService.showWalkByIdService(id);
    }

    // Endpoint para eliminar una reserva por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWalkById(@PathVariable("id") Integer id){
        boolean deleted = walkService.deleteWalkByIdService(id);

        if (deleted){
            return ResponseEntity.ok("Walk was deleted successfull");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Was not possible delete the walk !!!");
        }
    }
}
