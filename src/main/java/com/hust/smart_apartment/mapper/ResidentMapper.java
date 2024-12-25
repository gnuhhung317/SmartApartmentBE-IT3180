package com.hust.smart_apartment.mapper;

import com.hust.smart_apartment.dto.request.ResidentRequest;
import com.hust.smart_apartment.dto.response.ResidentResponse;
import com.hust.smart_apartment.entity.Resident;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ResidentMapper extends BaseMapper<ResidentRequest, ResidentResponse, Resident> {
    public abstract ResidentResponse entityToResponse(Resident resident);

    public abstract Resident requestToEntity(ResidentRequest residentRequest);

    public abstract List<ResidentResponse> entityListToResponseList(List<Resident> residents);
}
