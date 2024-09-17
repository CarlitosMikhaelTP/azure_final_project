package com.example.demo.application.exceptions.CommentWalkerExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentWalkerNotFoundException extends RuntimeException {
    public CommentWalkerNotFoundException(String message){
        super(message);
    }
}
