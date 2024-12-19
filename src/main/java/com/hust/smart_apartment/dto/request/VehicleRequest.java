package com.hust.smart_apartment.dto.request;

import com.hust.smart_apartment.entity.VehicleType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleRequest {

    private String licensePlate;

    private String name;

    private Long vehicleTypeId;

    private Long apartmentId;
}
