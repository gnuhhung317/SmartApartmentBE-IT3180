package com.hust.smart_apartment.dto.request;

import com.hust.smart_apartment.constants.FeeCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeTypeRequest {
    private Long feeTypeId;
    private FeeCategory category;
    private Integer unitPrice;
    private String description;
}
