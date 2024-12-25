package com.hust.smart_apartment.entity;

import com.hust.smart_apartment.constants.FeeCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle_types")
@Accessors(chain = true)
@Builder
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
