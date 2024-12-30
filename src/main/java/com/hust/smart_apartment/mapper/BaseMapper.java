package com.hust.smart_apartment.mapper;

import com.hust.smart_apartment.dto.response.ResidentResponse;
import com.hust.smart_apartment.entity.Resident;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

public abstract class BaseMapper<A,B,C> {
    public abstract B entityToResponse(C c);
    public abstract C requestToEntity(A a);
    @AfterMapping
    public void residentToResidentResponseAfter(Resident resident, @MappingTarget ResidentResponse residentResponse) {
        residentResponse.setCurrentLivingType( resident.getCurrentLivingType().toCodeNameResponse() );
        residentResponse.setHouseholdRole( resident.getHouseholdRole().toCodeNameResponse() );
        residentResponse.setGender( resident.getGender().toCodeNameResponse() );
    }
}
