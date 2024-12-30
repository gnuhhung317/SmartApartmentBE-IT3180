package com.hust.smart_apartment.mapper;

import com.hust.smart_apartment.dto.request.ApartmentRequest;
import com.hust.smart_apartment.dto.response.ApartmentResponse;
import com.hust.smart_apartment.dto.response.ResidentResponse;
import com.hust.smart_apartment.entity.Apartment;
import com.hust.smart_apartment.entity.Resident;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ApartmentMapper extends BaseMapper<ApartmentRequest, ApartmentResponse, Apartment> {
    @Override
    public abstract ApartmentResponse entityToResponse(Apartment apartment) ;

    @Override
    public abstract Apartment requestToEntity(ApartmentRequest request) ;

}
