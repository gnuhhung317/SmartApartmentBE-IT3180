package com.hust.smart_apartment.mapper;

import com.hust.smart_apartment.dto.request.CampaignRequest;
import com.hust.smart_apartment.dto.response.CampaignResponse;
import com.hust.smart_apartment.entity.Campaign;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CampaignMapper extends BaseMapper<CampaignRequest, CampaignResponse, Campaign> {
    @Override
    public abstract CampaignResponse entityToResponse(Campaign building);

    @Override
    public abstract Campaign requestToEntity(CampaignRequest buildingRequest);
}
