package com.hust.smart_apartment.mapper;

import com.hust.smart_apartment.dto.request.ChangeLivingTypeRequest;
import com.hust.smart_apartment.dto.response.ResidentChangeLogResponse;
import com.hust.smart_apartment.entity.ResidentChangeLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ResidentChangeLogMapper extends BaseMapper<ChangeLivingTypeRequest, ResidentChangeLogResponse, ResidentChangeLog> {
    @Override
    public abstract ResidentChangeLogResponse entityToResponse(ResidentChangeLog residentChangeLog) ;
    @Override
    public abstract ResidentChangeLog requestToEntity(ChangeLivingTypeRequest changeLivingTypeRequest) ;}
