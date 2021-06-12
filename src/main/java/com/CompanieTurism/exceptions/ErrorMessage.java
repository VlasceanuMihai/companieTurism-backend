package com.CompanieTurism.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    EMPLOYEE_ALREADY_EXISTS("employee.already.exists", "Employee already exists");

    private String key;
    private String message;

    ErrorMessage(String key, String message) {
        this.key = key;
        this.message = message;
    }
}
