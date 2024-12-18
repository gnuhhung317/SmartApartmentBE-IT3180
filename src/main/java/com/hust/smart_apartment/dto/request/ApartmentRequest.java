package com.hust.smart_apartment.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApartmentRequest {

    private String name;

    private String code;

    private Long area;

    private ResidentRequest owner;

    private List<ResidentRequest> residents;
}
