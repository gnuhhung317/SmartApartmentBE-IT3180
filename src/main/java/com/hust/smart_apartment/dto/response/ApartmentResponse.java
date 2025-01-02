package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.annotations.DbColumnMapper;
import com.hust.smart_apartment.annotations.QuickSearchDomain;
import com.hust.smart_apartment.constants.QuickSearchKeyOption;
import com.hust.smart_apartment.dto.AbstractAuditingDto;
import com.hust.smart_apartment.dto.model.QuickSearchInput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@QuickSearchDomain(tableName = """
        (SELECT a.*,r.full_name FROM apartments a
         LEFT JOIN residents r on a.resident_id = r.resident_id) as a
        """,isCustom = true)
public class ApartmentResponse extends AbstractAuditingDto {

    @DbColumnMapper("apartment_id")
    private Long apartmentId;

    @DbColumnMapper("name")
    @QuickSearchInput(columnName = "name",keyOption = QuickSearchKeyOption.LIKE)
    private String name;

    @DbColumnMapper("code")
    @QuickSearchInput(columnName = "code",keyOption = QuickSearchKeyOption.LIKE)
    private String code;

    @DbColumnMapper("area")
    private Long area;

    @DbColumnMapper("resident_id")
    private Long ownerId;

    @DbColumnMapper("floor_id")
    private Long floorId;

    @DbColumnMapper("full_name")
    @QuickSearchInput(columnName = "full_name",keyOption = QuickSearchKeyOption.LIKE)
    private String ownerName;

    private Long vehicleCount;

    private ResidentResponse owner;

    private List<ResidentResponse> residents;
}
