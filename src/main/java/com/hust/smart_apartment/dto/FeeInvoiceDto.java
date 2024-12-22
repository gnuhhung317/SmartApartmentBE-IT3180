package com.hust.smart_apartment.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeeInvoiceDto {

    private String feeName;
    private Integer feeAmount;
    private String feeDescription;

    public void addFeeAmount(Integer feeAmount) {
        if(feeAmount == null) {
            return;
        }
        this.feeAmount += feeAmount;
    }
}
