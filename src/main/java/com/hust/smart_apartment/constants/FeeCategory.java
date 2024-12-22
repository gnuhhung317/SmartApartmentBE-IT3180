package com.hust.smart_apartment.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FeeCategory {
    SERVICE_FEE("Phí dịch vụ"),
    MANAGEMENT_FEE("Phí quản lý"),
    PARKING_MOTORCYCLE("Phí gửi xe máy"),
    PARKING_CAR("Phí gửi ô tô"),;

    private final String name;
}
