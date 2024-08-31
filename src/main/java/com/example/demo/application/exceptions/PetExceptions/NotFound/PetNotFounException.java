package com.example.demo.application.exceptions.PetExceptions.NotFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PetNotFounException extends RuntimeException {

    public PetNotFounException(String message){
        super(message);
    }
}
