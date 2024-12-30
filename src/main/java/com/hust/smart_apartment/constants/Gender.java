package com.hust.smart_apartment.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender implements CodeNameProvider {
    NAM (0,"Nam"),
    NU (1,"Nữ"),
    GAY(2,"Bê đê nam"),
    LESBIAN(3,"Bê đê nữ");

    private final int code;
    private final String name;

    @Override
    public String getEnumName() {
        return name();
    }
}
