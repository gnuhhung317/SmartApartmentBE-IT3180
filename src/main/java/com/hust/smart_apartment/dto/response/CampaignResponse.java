package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.annotations.DbColumnMapper;
import com.hust.smart_apartment.annotations.QuickSearchDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@QuickSearchDomain(tableName = "campaigns")
public class CampaignResponse {
    @DbColumnMapper("id")
    private Long id;

    @DbColumnMapper("name")
    private String name;

    @DbColumnMapper("description")
    private String description;

    @DbColumnMapper("start_date")
    private Date startDate;

    @DbColumnMapper("end_date")
    private Date endDate;

    @DbColumnMapper("total_amount")
    private Long total = 0l;

}
