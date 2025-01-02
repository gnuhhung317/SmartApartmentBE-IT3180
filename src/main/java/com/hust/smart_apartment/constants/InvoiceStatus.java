package com.hust.smart_apartment.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum InvoiceStatus implements CodeNameProvider{
    DA_THANH_TOAN(0,"Đã thanh toán"),
    CHUA_THANH_TOAN(1,"Chưa thanh toán");
//    THANH_TOAN_THIEU(2,"Thanh toán thiếu"),
//    THANH_TOAN_QUA_HAN(3,"Thanh toán quá hạn"),
//    QUA_HAN(4,"Quá hạn");

    private final int code;
    private final String name;
    @Override
    public String getEnumName() {
        return name();
    }
}
