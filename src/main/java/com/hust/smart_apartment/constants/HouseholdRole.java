package com.hust.smart_apartment.constants;

import lombok.Getter;

@Getter
public enum HouseholdRole implements CodeNameProvider {
    CHILD(0,"Con"),
    MARRIAGE(1,"Vợ chồng"),
    PARENT(2,"Bố Mẹ"),
    RELATIVES(3, "Cô/Chú/Bác/Dì"),
    OTHER(4,"Khác");


    private final int code;
    private final String name;

    HouseholdRole(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
