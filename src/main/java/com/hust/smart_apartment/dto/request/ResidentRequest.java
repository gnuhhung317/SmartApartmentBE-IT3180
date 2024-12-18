package com.hust.smart_apartment.dto.request;

import com.hust.smart_apartment.constants.HouseholdRole;
import com.hust.smart_apartment.constants.LivingType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ResidentRequest {
    private Long residentId;

    private String fullName;

    private Date dateOfBirth;

    private String identityCardNumber;

    private LivingType currentLivingType;

    private HouseholdRole householdRole;
}
