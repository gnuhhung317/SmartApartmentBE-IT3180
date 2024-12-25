package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.annotations.DbColumnMapper;
import com.hust.smart_apartment.annotations.QuickSearchDomain;
import com.hust.smart_apartment.constants.FeeCategory;
import com.hust.smart_apartment.constants.QuickSearchKeyOption;
import com.hust.smart_apartment.dto.model.QuickSearchInput;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@QuickSearchDomain(tableName = "fee_types")
public class FeeTypeResponse {

    @DbColumnMapper("fee_type_id")
    private Long feeTypeId;

    @DbColumnMapper("category")
    @QuickSearchInput(columnName = "category",keyOption = QuickSearchKeyOption.ILIKE)
    private FeeCategory category;

    @DbColumnMapper("unit_price")
    private Long unitPrice;

    @QuickSearchInput(columnName = "name",keyOption = QuickSearchKeyOption.ILIKE)
    @DbColumnMapper("description")
    private String description;
}