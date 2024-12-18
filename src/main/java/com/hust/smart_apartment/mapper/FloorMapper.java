package com.hust.smart_apartment.mapper;

import com.hust.smart_apartment.dto.request.FloorRequest;
import com.hust.smart_apartment.dto.response.FloorResponse;
import com.hust.smart_apartment.entity.Floor;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class FloorMapper extends BaseMapper<FloorRequest, FloorResponse, Floor>
{
    @Override
    public abstract FloorResponse entityToResponse(Floor floor);

    @Override
    public abstract Floor requestToEntity(FloorRequest floorRequest);
}
