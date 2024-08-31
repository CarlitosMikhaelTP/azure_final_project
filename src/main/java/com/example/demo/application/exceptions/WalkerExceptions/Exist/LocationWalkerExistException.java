package com.example.demo.application.exceptions.WalkerExceptions.Exist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LocationWalkerExistException extends RuntimeException {
    public LocationWalkerExistException(String message){
        super(message);
    }
}
