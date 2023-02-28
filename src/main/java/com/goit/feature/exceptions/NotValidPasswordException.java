package com.goit.feature.exceptions;

public class NotValidPasswordException extends RuntimeException {
    public NotValidPasswordException(String message) {
        super(message);
    }
}
