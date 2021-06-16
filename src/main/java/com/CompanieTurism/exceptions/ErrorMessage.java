package com.CompanieTurism.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    EMPLOYEE_ALREADY_EXISTS("employee.already.exists", "Employee already exists"),
    EMPLOYEE_NOT_FOUND("employee.not.found", "Employee not found"),
    CANNOT_UPDATE_EMPLOYEE("cannot.update.employee", "Cannot update employee"),
    DOCUMENT_NOT_FOUND("document.not.found", "Document not found"),
    FLIGHT_NOT_FOUND("flight.not.found", "Flight not found"),
    HOTEL_NOT_FOUND("hotel.not.found", "Hotel not found"),
    HOTEL_ALREADY_EXISTS("hotel.already.exists", "Hotel already exists");

    private String key;
    private String message;

    ErrorMessage(String key, String message) {
        this.key = key;
        this.message = message;
    }
}
