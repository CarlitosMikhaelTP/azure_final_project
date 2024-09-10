package com.example.demo.application.exceptions.PetExceptions.NotFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BadHabitsNotFoundException extends RuntimeException{
    public BadHabitsNotFoundException(String message){
        super(message);
    }
}