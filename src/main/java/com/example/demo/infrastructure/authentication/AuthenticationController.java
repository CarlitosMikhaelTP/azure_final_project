package com.example.demo.infrastructure.authentication;

import com.example.demo.application.exceptions.UserExceptions.NotFound.TypeUserNotFoundException;
import com.example.demo.domain.entity.user.TypeUser;
import com.example.demo.domain.repository.User.TypeUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://api-uywalky-htb2bpd4bkaqfvau.eastus-01.azurewebsites.net"})
public class AuthenticationController {

    private final AuthenticationService service;
    private final TypeUserRepository typeUserRepository;

    //ENDPOINT para acceder a los servicios de Registros
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        Integer idTypeUser = request.getIdUserType();
        TypeUser tipoUsuario = typeUserRepository.findById(idTypeUser)
                .orElseThrow(() -> new TypeUserNotFoundException("Tipo de usuario no encontrado"));
        //Llamando al servicio de registro y pasándole el tipo de usuario asociado
        AuthenticationResponse response = service.register(request, idTypeUser);
        return ResponseEntity.ok(response);
    }

    // ENDPOINT para acceder a los servicios de autenticación
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse response = service.authenticate(request);

        return ResponseEntity.ok(service.authenticate(request));
    }
}
