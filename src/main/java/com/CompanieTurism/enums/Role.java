package com.CompanieTurism.enums;

public enum Role {
    ROLE_ADMIN,
    ROLE_MANAGER_HR,
    ROLE_USER;

    public boolean isUser() {
        return this.equals(ROLE_USER);
    }

    public boolean isManagerHr() {
        return this.equals(ROLE_MANAGER_HR);
    }

    public boolean isAdmin() {
        return this.equals(ROLE_ADMIN);
    }
}
