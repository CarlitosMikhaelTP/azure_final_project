package com.example.demo.application.exceptions.ChatExceptions.NotFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TypeMessageNotFoundException extends RuntimeException {

    public TypeMessageNotFoundException(String message){
        super(message);
    }
}
