package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.annotations.DbColumnMapper;
import com.hust.smart_apartment.annotations.QuickSearchDomain;
import com.hust.smart_apartment.constants.FeeCategory;
import com.hust.smart_apartment.constants.QuickSearchKeyOption;
import com.hust.smart_apartment.dto.model.QuickSearchInput;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@QuickSearchDomain(tableName = "vehicle_types")
public class VehicleTypeResponse {

    @DbColumnMapper("vehicle_type_id")
    private Long vehicleTypeId;

//    @DbColumnMapper("name")
//    private String name;

    private FeeCategory feeCategory;

    @DbColumnMapper("unit_price")
    private Integer unitPrice;

}
