package com.example.demo.application.exceptions.TransactionExceptions.NotFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StateTransactionNotFoundException extends  RuntimeException{

    public StateTransactionNotFoundException(String message){
        super(message);
    }
}
