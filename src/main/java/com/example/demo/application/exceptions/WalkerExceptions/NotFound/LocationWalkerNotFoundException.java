package com.example.demo.application.exceptions.WalkerExceptions.NotFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LocationWalkerNotFoundException extends RuntimeException {

    public LocationWalkerNotFoundException(String message){
        super(message);
    }
}
