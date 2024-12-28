package com.hust.smart_apartment.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationRequest {

    private Integer amount;

    private Long apartmentId;

    private Long campaignId;
}
