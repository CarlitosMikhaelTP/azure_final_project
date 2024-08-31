package com.example.demo.application.exceptions.WalkExceptions.Exists;

import com.example.demo.application.exceptions.WalkerExceptions.Exist.WalkerExistException;

public class WalkExistException extends RuntimeException{

    public WalkExistException(String message) {
        super(message);
    }
}
