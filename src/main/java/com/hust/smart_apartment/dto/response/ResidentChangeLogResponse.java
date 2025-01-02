package com.hust.smart_apartment.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust.smart_apartment.annotations.DbColumnMapper;
import com.hust.smart_apartment.annotations.QuickSearchDomain;
import com.hust.smart_apartment.constants.LivingType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@QuickSearchDomain(tableName = "resident_change_log")
public class ResidentChangeLogResponse {

    @DbColumnMapper(value = "id")
    private Long id;

    @DbColumnMapper(value = "resident_id")
    private Long residentId;

    @DbColumnMapper(value = "apartment_id")
    private Long apartmentId;

    @JsonIgnore
    @DbColumnMapper(value = "resident")
    private String residentString;

    private ResidentResponse resident;

    @JsonIgnore
    @DbColumnMapper(value = "apartment")
    private String apartmentString;

    private ApartmentResponse apartment;

    @DbColumnMapper(value = "last_type")
    private int lastTypeCode;

    private CodeNameResponse lastType;

    @DbColumnMapper(value = "change_type")
    private int changeTypeCode;

    private CodeNameResponse changeType;

    @DbColumnMapper(value = "change_date")
    private Date changeDate;

    @DbColumnMapper(value = "notes")
    private String notes;

    public void setResidentString(String residentString) throws JsonProcessingException {
        this.residentString = residentString;
        this.resident = new ObjectMapper().convertValue(residentString, ResidentResponse.class);
    }

    public void setApartmentString(String apartmentString) throws JsonProcessingException {
        this.apartmentString = apartmentString;
        this.apartment = new ObjectMapper().convertValue(apartmentString, ApartmentResponse.class);
    }
    public void setChangeTypeCode(int changeTypeCode) {
        this.changeTypeCode = changeTypeCode;
        this.changeType = LivingType.values()[changeTypeCode].toCodeNameResponse();
    }
    public void setLastTypeCode(int lastTypeCode) {
        this.lastTypeCode = lastTypeCode;
        this.lastType = LivingType.values()[lastTypeCode].toCodeNameResponse();
    }
    public void setLastType(LivingType lastType) {
        this.lastType = lastType.toCodeNameResponse();
    }
    public void setChangeType(LivingType changeType) {
        this.changeType = changeType.toCodeNameResponse();
    }
}
