package com.example.demo.application.exceptions.ChatExceptions.Exist;


public class RoomExistException extends RuntimeException {

    public RoomExistException(String message){
        super(message);
    }
}
