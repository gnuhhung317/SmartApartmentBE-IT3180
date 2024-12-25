package com.hust.smart_apartment.dto.request;

import com.hust.smart_apartment.entity.Period;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class CampaignRequest {

    private String name;

    private String description;

    private Date startDate;

    private Date endDate;

}
