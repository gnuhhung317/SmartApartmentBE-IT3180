package com.hust.smart_apartment.constants;

import lombok.Getter;

@Getter
public enum LivingType implements CodeNameProvider {
    TAM_TRU(0,"Tạm trú"),
    TAM_VANG(1,"Tạm vắng"),
    THUONG_TRU(2,"Thường trú"),
    THUONG_VANG(3,"Thường vắng"),
    O_NGOAI(4,"Ở ngoài"),
    CHET(5,"Chết");

    private final int code;
    private final String name;

    LivingType(int code, String name) {
        this.code = code;
        this.name = name;
    }
    @Override
    public String getEnumName() {
        return name();
    }


}
