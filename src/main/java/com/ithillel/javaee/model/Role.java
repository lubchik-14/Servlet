package com.ithillel.javaee.model;

import com.google.common.base.CaseFormat;

public enum Role {
    ADMIN,
    SUPPORT,
    USER;

    @Override
    public String toString() {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, this.name());
    }
}
