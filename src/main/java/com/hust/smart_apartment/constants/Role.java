package com.hust.smart_apartment.constants;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String value;

    Role(String value) {
        this.value = value;
    }
    public static Role getRole(String value) {
        return value.equals(ROLE_ADMIN.value) ? ROLE_ADMIN : ROLE_USER;
    }
}
