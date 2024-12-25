package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.annotations.DbColumnMapper;
import com.hust.smart_apartment.annotations.QuickSearchDomain;
import com.hust.smart_apartment.entity.Apartment;
import com.hust.smart_apartment.entity.Campaign;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@QuickSearchDomain(tableName = "donations")
public class DonationResponse {

    @DbColumnMapper("id")
    private Long id;

    @DbColumnMapper("amount")
    private Integer amount;

    @DbColumnMapper("apartment_id")
    private Long apartmentId;

    @DbColumnMapper("campaign_id")
    private Long campaignId;

    private Apartment apartment;

    private Campaign campaign;
}