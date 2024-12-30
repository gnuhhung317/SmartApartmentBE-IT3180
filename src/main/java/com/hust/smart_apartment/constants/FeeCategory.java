package com.hust.smart_apartment.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FeeCategory implements CodeNameProvider{
    SERVICE_FEE(0,"Phí dịch vụ"),
    MANAGEMENT_FEE(1,"Phí quản lý"),
    PARKING_MOTORCYCLE(2,"Phí gửi xe máy"),
    PARKING_CAR(3,"Phí gửi ô tô"),
    PARKING_BICYCLE(4,"Phí gửi xe đạp"),
    PARKING_OTHER(5,"Phí gữi khác");

    private final int code;
    private final String name;

    @Override
    public String getEnumName() {
        return name();
    }
}
