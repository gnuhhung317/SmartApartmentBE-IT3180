package com.hust.smart_apartment.entity;

import com.hust.smart_apartment.constants.FeeCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle_types")
@Accessors(chain = true)
public class VehicleType extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleTypeId;

    @Column(name = "name",nullable = false)
    @Enumerated(EnumType.STRING)
    private FeeCategory feeCategory;

    @Column(name = "unit_price",nullable = false)
    private Integer unitPrice;
}
