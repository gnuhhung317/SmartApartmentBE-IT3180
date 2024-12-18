package com.hust.smart_apartment.dto.request;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeTypeRequest {
    private Long feeTypeId;
    private BigDecimal unitPrice;
    private String description;
}
