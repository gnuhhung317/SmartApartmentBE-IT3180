package com.hust.smart_apartment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hust.smart_apartment.annotations.DbColumnMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractAuditingDto {

    @DbColumnMapper("created_by")
    private String createdBy;

    @DbColumnMapper("created_at")
    private Date createdAt;

    @DbColumnMapper("updated_by")
    private String updatedBy;

    @DbColumnMapper("updated_at")
    private Date updatedAt;

    private String createByName;

    private String updateByName;
}
