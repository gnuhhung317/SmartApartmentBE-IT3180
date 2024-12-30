package com.hust.smart_apartment.entity;

import com.hust.smart_apartment.constants.Gender;
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

    @Column(name = "full_name",nullable = false)
    private String fullName;

    private Date dateOfBirth;

    @Column(name = "identity_card_number",nullable = false)
    private String identityCardNumber;

    @Column(name = "current_living_type",nullable = false)
    private LivingType currentLivingType;

    @Column(name = "household_role",nullable = false)
    private HouseholdRole householdRole;

    private String contact;

    @Column(name = "gender",nullable = false)
    private Gender gender;

    private String job;

    private String hometown;

    private String avatar = "https://pic.re/image";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment livingApartment;
}
