package com.hust.smart_apartment.entity;

import com.hust.smart_apartment.constants.HouseholdRole;
import com.hust.smart_apartment.constants.LivingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "residents")
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long residentId;

    private String fullName;

    private Date dateOfBirth;

    private String identityCardNumber;

    private LivingType currentLivingType;

    private HouseholdRole householdRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment livingApartment;
}
