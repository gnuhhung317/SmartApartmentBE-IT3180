package com.hust.smart_apartment.mapper;

import com.hust.smart_apartment.dto.request.PeriodRequest;
import com.hust.smart_apartment.dto.response.PeriodResponse;
import com.hust.smart_apartment.entity.Period;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PeriodMapper extends BaseMapper<PeriodRequest, PeriodResponse, Period> {
    @Override
    public abstract PeriodResponse entityToResponse(Period period);
    @Override
    public abstract Period requestToEntity(PeriodRequest periodRequest);}
