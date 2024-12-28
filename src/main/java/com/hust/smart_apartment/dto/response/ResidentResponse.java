package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.annotations.DbColumnMapper;
import com.hust.smart_apartment.annotations.QuickSearchDomain;
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

    @DbColumnMapper("identity_card_number")
    private String identityCardNumber;

    @DbColumnMapper("current_living_type")
    private LivingType currentLivingType;

    @DbColumnMapper("household_role")
    private HouseholdRole householdRole;

}
