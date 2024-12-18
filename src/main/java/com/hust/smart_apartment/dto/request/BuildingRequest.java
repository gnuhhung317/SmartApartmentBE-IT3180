package com.hust.smart_apartment.dto.request;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingRequest {
    private String buildingCode;
    private String buildingName;
}
