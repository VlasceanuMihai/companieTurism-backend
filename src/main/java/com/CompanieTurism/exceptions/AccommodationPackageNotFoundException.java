package com.CompanieTurism.exceptions;

public class AccommodationPackageNotFoundException extends RuntimeException {

    public AccommodationPackageNotFoundException() {
    }

    public AccommodationPackageNotFoundException(String message) {
        super(message);
    }

    public AccommodationPackageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccommodationPackageNotFoundException(ErrorMessage error) {
        super(error.getMessage());
    }
}
