package com.goit.feature.exceptions;

public class NotValidNoteContentException extends RuntimeException {
    public NotValidNoteContentException(String message) {
        super(message);
    }
}
