package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.constants.FeeCategory;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleTypeResponse {

    private Long vehicleTypeId;

    private FeeCategory feeCategory;

    private Integer unitPrice;
}
