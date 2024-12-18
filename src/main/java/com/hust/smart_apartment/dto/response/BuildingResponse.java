package com.hust.smart_apartment.dto.response;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingResponse {
    private Long id;
    private String buildingCode;
    private String buildingName;
}
