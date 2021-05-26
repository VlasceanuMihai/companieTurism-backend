package com.CompanieTurism.exceptions;

public class HotelExistsException extends RuntimeException {

    public HotelExistsException() {
    }

    public HotelExistsException(String message) {
        super(message);
    }

    public HotelExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
