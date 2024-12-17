package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.entity.Resident;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ApartmentResponse {
    private Long apartmentId;

    private String name;

    private String code;

    private Long area;

    private ResidentResponse owner;

    private List<Resident> residents;
}
