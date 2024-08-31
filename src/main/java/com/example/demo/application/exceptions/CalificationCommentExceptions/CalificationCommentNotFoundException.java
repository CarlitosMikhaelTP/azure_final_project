package com.example.demo.application.exceptions.CalificationCommentExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CalificationCommentNotFoundException extends RuntimeException {
    public CalificationCommentNotFoundException(String message){
        super(message);
    }
}
