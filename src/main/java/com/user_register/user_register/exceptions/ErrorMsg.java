package com.user_register.user_register.exceptions;

public class ErrorMsg extends RuntimeException {
    public ErrorMsg(String message){
        super(message);
    }
}