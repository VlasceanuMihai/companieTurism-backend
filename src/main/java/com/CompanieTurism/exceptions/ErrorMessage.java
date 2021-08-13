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
    HOTEL_ALREADY_EXISTS("hotel.already.exists", "Hotel already exists"),
    ACCOMMODATION_PACKAGE_NOT_FOUND("accommodation.package.not.found", "Accommodation package not found"),
    INVALID_CURRENT_PASSWORD("invalid.current.password", "Invalid current password"),
    INVALID_NEW_PASSWORD("invalid.new.password", "Invalid new password"),
    EQUALS_PASSWORDS("new.password.equals.current.password", "New password is equals with current password");

    private String key;
    private String message;

    ErrorMessage(String key, String message) {
        this.key = key;
        this.message = message;
    }
}
