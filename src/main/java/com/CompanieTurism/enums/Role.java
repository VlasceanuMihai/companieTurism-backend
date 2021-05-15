package com.CompanieTurism.enums;

public enum Role {
    ROLE_ADMIN,
    ROLE_USER;

    public boolean isUser() {
        return this.equals(ROLE_USER);
    }

    public boolean isAdmin() {
        return this.equals(ROLE_ADMIN);
    }
}
