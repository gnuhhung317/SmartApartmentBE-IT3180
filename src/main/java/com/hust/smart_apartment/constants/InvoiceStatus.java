package com.hust.smart_apartment.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum InvoiceStatus {
    DA_THANH_TOAN("Đã thanh toán"),
    CHUA_THANH_TOAN("Chưa thanh toán"),
    THANH_TOAN_THIEU("Thanh toán thiếu"),
    THANH_TOAN_QUA_HAN("Thanh toán quá hạn"),
    QUA_HAN("Quá hạn");

    private final String name;
}
