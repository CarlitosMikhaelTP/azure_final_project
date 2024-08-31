package com.example.demo.application.exceptions.OwnerExceptions.NotFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OwnerNotFoundException extends RuntimeException {

    public OwnerNotFoundException(String message){
        super(message);
    }
}
