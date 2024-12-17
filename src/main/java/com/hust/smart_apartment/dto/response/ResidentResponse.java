package com.hust.smart_apartment.dto.response;

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
public class ResidentResponse {

    private Long residentId;

    private String fullName;

    private Date dateOfBirth;

    private String identityCardNumber;

    private LivingType currentLivingType;

    private HouseholdRole householdRole;

}
