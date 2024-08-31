package com.example.demo.infrastructure.authentication;


public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request, Integer idTipoUsuario);
    AuthenticationResponse authenticate(AuthenticationRequest request);

}

