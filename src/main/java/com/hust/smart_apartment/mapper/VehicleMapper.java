package com.hust.smart_apartment.mapper;

import com.hust.smart_apartment.dto.request.VehicleRequest;
import com.hust.smart_apartment.dto.response.VehicleResponse;
import com.hust.smart_apartment.entity.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class VehicleMapper extends BaseMapper<VehicleRequest, VehicleResponse, Vehicle> {
    @Override
    public abstract VehicleResponse entityToResponse(Vehicle vehicle);
    @Override
    public abstract Vehicle requestToEntity(VehicleRequest vehicleRequest);}
