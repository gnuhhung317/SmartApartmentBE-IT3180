package com.hust.smart_apartment.dto.response;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FloorResponse {
    private Long id;
    private String name;
    private Integer floorNumber;
    private BuildingResponse building;
    private List<ApartmentResponse> apartments;
}
