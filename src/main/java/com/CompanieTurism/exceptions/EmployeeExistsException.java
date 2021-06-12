package com.CompanieTurism.exceptions;

public class EmployeeExistsException extends RuntimeException{

    public EmployeeExistsException() {
    }

    public EmployeeExistsException(String message) {
        super(message);
    }

    public EmployeeExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeExistsException(ErrorMessage error) {
        super(error.getMessage());
    }
}
