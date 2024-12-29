package com.hust.smart_apartment.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hust.smart_apartment.annotations.DbColumnMapper;
import com.hust.smart_apartment.annotations.QuickSearchDomain;
import com.hust.smart_apartment.constants.Gender;
import com.hust.smart_apartment.constants.HouseholdRole;
import com.hust.smart_apartment.constants.LivingType;
import com.hust.smart_apartment.entity.Apartment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@QuickSearchDomain(tableName = "residents")
public class ResidentResponse {

    @DbColumnMapper("resident_id")
    private Long residentId;

    @DbColumnMapper("full_name")
    private String fullName;

    @DbColumnMapper("date_of_birth")
    private Date dateOfBirth;

    @DbColumnMapper("avatar")
    private String avatar = "https://pic.re/image";

    @DbColumnMapper("identity_card_number")
    private String identityCardNumber;

    @JsonIgnore
    @DbColumnMapper("current_living_type")
    private int currentLivingTypeId;

    private LivingType currentLivingType;

    @JsonIgnore
    @DbColumnMapper("household_role")
    private int householdRoleId;

    private HouseholdRole householdRole;

    @DbColumnMapper("gender")
    private int genderId;

    private Gender gender;

    @DbColumnMapper("contact")
    private String contact;

    void setCurrentLivingTypeId( int currentLivingTypeId ) {
        this.currentLivingTypeId = currentLivingTypeId;
        currentLivingType = LivingType.values()[currentLivingTypeId];
    }

    void setHouseholdRoleId( int householdRoleId ) {
        this.householdRoleId = householdRoleId;
        householdRole = HouseholdRole.values()[householdRoleId];
    }

    void setGenderId( int genderId ) {
        this.genderId = genderId;
        gender = Gender.values()[genderId];
    }
}
