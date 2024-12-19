package com.hust.smart_apartment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeTypeRequest {
    private Long feeTypeId;
    private Integer unitPrice;
    private String description;
}
