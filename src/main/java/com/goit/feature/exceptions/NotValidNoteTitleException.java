package com.goit.feature.exceptions;

public class NotValidNoteTitleException extends RuntimeException {
    public NotValidNoteTitleException(String message) {
        super(message);
    }
}
