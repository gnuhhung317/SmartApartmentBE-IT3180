package com.hust.smart_apartment.mapper;

import com.hust.smart_apartment.dto.request.BuildingRequest;
import com.hust.smart_apartment.dto.response.BuildingResponse;
import com.hust.smart_apartment.entity.Building;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class BuildingMapper extends BaseMapper<BuildingRequest,BuildingResponse, Building> {
    @Override
    public abstract BuildingResponse entityToResponse(Building building) ;

    @Override
    public abstract Building requestToEntity(BuildingRequest buildingRequest) ;
}
