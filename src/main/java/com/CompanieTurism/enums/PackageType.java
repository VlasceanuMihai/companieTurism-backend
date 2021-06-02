package com.CompanieTurism.enums;

import lombok.Getter;

public enum PackageType {
    STANDARD(1),
    PREMIUM(500),
    VIP(1000);

    @Getter
    final Integer value;

    PackageType(Integer value) {
        this.value = value;
    }
}
