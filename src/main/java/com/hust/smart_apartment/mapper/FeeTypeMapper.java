package com.hust.smart_apartment.mapper;

import com.hust.smart_apartment.dto.request.FeeTypeRequest;
import com.hust.smart_apartment.dto.response.FeeTypeResponse;
import com.hust.smart_apartment.entity.FeeType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class FeeTypeMapper extends BaseMapper<FeeTypeRequest, FeeTypeResponse, FeeType> {
    @Override
    public abstract FeeTypeResponse entityToResponse(FeeType feeType);

    @Override
    public abstract FeeType requestToEntity(FeeTypeRequest feeTypeRequest);
}
