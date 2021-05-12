package com.CompanieTurism.enums;

public enum Role {
    ROLE_USER;

    public boolean isUser() {
        return this.equals(ROLE_USER);
    }
}
