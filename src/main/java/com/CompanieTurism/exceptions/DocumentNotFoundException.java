package com.CompanieTurism.exceptions;

public class DocumentNotFoundException extends RuntimeException {
    public DocumentNotFoundException() {
    }

    public DocumentNotFoundException(String message) {
        super(message);
    }

    public DocumentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentNotFoundException(ErrorMessage error) {
        super(error.getMessage());
    }
}
