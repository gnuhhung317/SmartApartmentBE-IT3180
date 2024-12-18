package com.hust.smart_apartment.constants;

import lombok.Getter;

@Getter
public enum HouseholdRole implements CodeNameProvider {
    CHILD(0,"Con"),
    MARRIAGE(1,"Vợ chồng"),
    PARENT(2,"Bố Mẹ");

    private final int code;
    private final String name;

    HouseholdRole(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
