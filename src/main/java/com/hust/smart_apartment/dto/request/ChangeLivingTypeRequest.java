package com.hust.smart_apartment.dto.request;

import com.hust.smart_apartment.constants.LivingType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeLivingTypeRequest {
    private LivingType newLivingType;
    private String notes;
}
