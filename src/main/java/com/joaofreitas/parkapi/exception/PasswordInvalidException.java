package com.joaofreitas.parkapi.exception;

public class PasswordInvalidException extends RuntimeException{
    public PasswordInvalidException(String message) {
    super(message);
    }
}