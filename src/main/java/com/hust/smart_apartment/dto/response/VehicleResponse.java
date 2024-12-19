package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.entity.Apartment;
import com.hust.smart_apartment.entity.VehicleType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {
    private Long vehicleId;

    private String licensePlate;

    private String name;

    private VehicleTypeResponse vehicleType;

    private ApartmentResponse apartment;
}
