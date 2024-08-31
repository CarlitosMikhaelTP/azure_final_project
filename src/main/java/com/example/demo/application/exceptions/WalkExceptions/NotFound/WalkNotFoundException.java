package com.example.demo.application.exceptions.WalkExceptions.NotFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WalkNotFoundException extends RuntimeException{

    public WalkNotFoundException(String message){
        super(message);
    }
}
