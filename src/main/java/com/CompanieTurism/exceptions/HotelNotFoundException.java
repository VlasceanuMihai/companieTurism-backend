package com.CompanieTurism.exceptions;

public class HotelNotFoundException extends RuntimeException {

    public HotelNotFoundException() {
    }

    public HotelNotFoundException(String message) {
        super(message);
    }

    public HotelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HotelNotFoundException(ErrorMessage error) {
        super(error.getMessage());
    }
}
