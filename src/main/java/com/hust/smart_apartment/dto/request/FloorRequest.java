package com.hust.smart_apartment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FloorRequest {
    private Long id;
    private String name;
    private Integer floorNumber;
    private Long buildingId;
}