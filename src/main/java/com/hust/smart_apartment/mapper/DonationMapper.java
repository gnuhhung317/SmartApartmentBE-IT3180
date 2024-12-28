package com.hust.smart_apartment.mapper;

import com.hust.smart_apartment.dto.request.DonationRequest;
import com.hust.smart_apartment.dto.response.DonationResponse;
import com.hust.smart_apartment.entity.Donation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DonationMapper extends BaseMapper<DonationRequest, DonationResponse, Donation> {
    @Override
    public abstract DonationResponse entityToResponse(Donation donation);

    @Override
    public abstract Donation requestToEntity(DonationRequest donationRequest);
}
