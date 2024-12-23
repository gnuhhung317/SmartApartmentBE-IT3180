package com.hust.smart_apartment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "apartments")
public class Apartment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apartmentId;

    private String name;

    private String code;

    private Long area;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident owner;

    @OneToMany(mappedBy = "livingApartment")
    private List<Resident> residents;

    @OneToMany(mappedBy = "apartment")
    private List<Vehicle> vehicles;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @ManyToOne
    @JoinColumn(name = "management_fee_id")
    private FeeType managementFee;

    @ManyToOne
    @JoinColumn(name = "service_fee_id")
    private FeeType serviceFee;
}
