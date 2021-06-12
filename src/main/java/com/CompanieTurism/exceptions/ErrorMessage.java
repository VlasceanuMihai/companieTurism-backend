package com.CompanieTurism.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    EMPLOYEE_ALREADY_EXISTS("employee.already.exists", "Employee already exists"),
    EMPLOYEE_NOT_FOUND("employee.not.found", "Employee not found"),
    CANNOT_UPDATE_EMPLOYEE("cannot.update.employee", "Cannot update employee");

    private String key;
    private String message;

    ErrorMessage(String key, String message) {
        this.key = key;
        this.message = message;
    }
}
