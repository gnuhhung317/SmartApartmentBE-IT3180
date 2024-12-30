package com.hust.smart_apartment.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DonationRequest {

    private Integer amount;

    private Date donationDate;

    private Long apartmentId;

    private Long campaignId;
}
