package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.annotations.DbColumnMapper;
import com.hust.smart_apartment.annotations.QuickSearchDomain;
import com.hust.smart_apartment.dto.AbstractAuditingDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@QuickSearchDomain(tableName = "apartments")
public class ApartmentResponse extends AbstractAuditingDto {

    @DbColumnMapper("apartment_id")
    private Long apartmentId;

    @DbColumnMapper("name")
    private String name;

    @DbColumnMapper("code")
    private String code;

    @DbColumnMapper("area")
    private Long area;

    @DbColumnMapper("resident_id")
    private Long ownerId;

    private Long vehicleCount;

    private ResidentResponse owner;

    private List<ResidentResponse> residents;
}
