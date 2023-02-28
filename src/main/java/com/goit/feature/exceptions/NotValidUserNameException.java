package com.goit.feature.exceptions;

public class NotValidUserNameException extends RuntimeException {
    public NotValidUserNameException(String message) {
        super(message);
    }
}
