package com.example.demo.infrastructure.web.controller;

import com.example.demo.application.exceptions.WalkerExceptions.NotFound.WalkerNotFoundException;
import com.example.demo.application.service.WalkerService.WalkerService;

import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.domain.repository.Walker.WalkerRepository;
import com.example.demo.infrastructure.web.projection.classBased.WalkerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.WalkerProjection;

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
@RequestMapping("/api/v1/walker")
@RequiredArgsConstructor
public class WalkerController {

    // Inicializamos las clases para trabajar con este constructor con "final"
    private final WalkerService walkerService;
    private final WalkerRepository walkerRepository;

    // Endpoint para registrar Walker
    @PostMapping("/register")
    public ResponseEntity<WalkerDTO> registerWalker(
            @RequestBody WalkerDTO walkerDTO) {
        WalkerDTO paseadorRegistrado = walkerService.registerWalkerService(walkerDTO);
        return ResponseEntity.ok().body(paseadorRegistrado);
    }

    // Endpoint para editar Walker
    @PutMapping("/edit/{id}")
    public ResponseEntity<WalkerDTO> editWalker(
            @PathVariable("id") Integer id,
            @RequestBody WalkerDTO walkerDTO
    ) {
        WalkerDTO updatedWalker = walkerService.editWalkerService(id, walkerDTO);
        return ResponseEntity.ok(updatedWalker);
    }

    // Endpoint para listar a todos los walker usando proyecciones
    @GetMapping("/findAllWalkers")
    public List<WalkerProjection> showAllWalkers() {
        return walkerService.showAllWalkersService(); // Obtiene todos los walker con la proyección
    }

    // Endpoint para listar un paseador por su ID
    @GetMapping("/findWalkerById/{id}")
    public Optional<WalkerProjection> findWalkerById(@PathVariable Integer id) {
        return walkerService.findWalkerByIdService(id);
    }

    // Endpoint para eliminar un paseador por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWalkerById(@PathVariable("id") Integer id) {
        boolean deleted = walkerService.deleteWalkerByIdService(id);

        if (deleted) {
            return ResponseEntity.ok("Walker was deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Was not possible found the Walker for delete him");
        }
    }

    @PostMapping("/{id}/foto")
    public ResponseEntity<String> editFotoWalker(
            @PathVariable Integer id,
            @RequestParam("foto") MultipartFile foto) {
        try {
            walkerService.editFotoWalkerService(id, foto);
            return ResponseEntity.ok("Updated walker's foto");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There's an error during foto's deleting " + e.getMessage());
        }
    }

    @GetMapping("/foto/{id}")
    public ResponseEntity<Resource> obtenerFotoPaseador(@PathVariable Integer id) {
        Walker paseador = walkerRepository.findById(id)
                .orElseThrow(() -> new WalkerNotFoundException("Walker not found"));

        String rutaFoto = paseador.getFoto();

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
                throw new FileNotFoundException("Was not possible find or read the walker's image");
            }
        } catch (MalformedURLException | FileNotFoundException e) {
            // Manejo de excepciones si el archivo no se encuentra o no se puede leer
            // Devolver un error o un recurso vacío en la respuesta
            return ResponseEntity.notFound().build();
        }
    }

}
    // Creacón de los ENDPOINTS
    // ENDPOINT PARA REGISTRAR NUEVOS PASEADORES

