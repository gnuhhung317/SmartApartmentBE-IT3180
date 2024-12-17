package com.hust.smart_apartment.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApartmentRequest {

    private String name;

    private String code;

    private Long area;
}
