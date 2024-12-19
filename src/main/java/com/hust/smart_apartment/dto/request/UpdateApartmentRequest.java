package com.hust.smart_apartment.dto.request;

import com.hust.smart_apartment.entity.Resident;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateApartmentRequest {

    Resident owner;
    List<ResidentRequest> residents;
}
