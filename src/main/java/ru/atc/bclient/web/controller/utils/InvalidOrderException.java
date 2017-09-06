package ru.atc.bclient.web.controller.utils;

public class InvalidOrderException extends RuntimeException {
    public InvalidOrderException(String message){
        super(message);
    }

}
