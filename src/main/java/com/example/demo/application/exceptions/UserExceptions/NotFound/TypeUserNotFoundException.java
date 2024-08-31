package com.example.demo.application.exceptions.UserExceptions.NotFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Envía información al que consume la API-REST sobre el error 404 Not Found
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TypeUserNotFoundException extends RuntimeException {

    // Excepción personalizada para el caso de TipoUsuario no encontrado
    public TypeUserNotFoundException(String message){
        super(message);
    }
}
