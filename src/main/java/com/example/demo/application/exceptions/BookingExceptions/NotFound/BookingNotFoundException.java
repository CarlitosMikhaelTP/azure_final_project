package com.example.demo.application.exceptions.BookingExceptions.NotFound;

public class BookingNotFoundException extends RuntimeException{

    public BookingNotFoundException(String message){
        super(message);
    }
}
