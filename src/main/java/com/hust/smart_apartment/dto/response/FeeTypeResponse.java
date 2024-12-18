package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.constants.FeeCategory;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeTypeResponse {
    private Long feeTypeId;
    private FeeCategory category;
    private BigDecimal unitPrice;
    private String description;
}