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
public class Resident extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long residentId;

    private String fullName;

    private Date dateOfBirth;

    @Column(name = "identity_card_number",nullable = false)
    private String identityCardNumber;

    @Column(name = "current_living_type",nullable = false)
    private LivingType currentLivingType;

    @Column(name = "household_role",nullable = false)
    private HouseholdRole householdRole;

    private String avatar = "https://pic.re/image";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id",nullable = false)
    private Apartment livingApartment;
}
