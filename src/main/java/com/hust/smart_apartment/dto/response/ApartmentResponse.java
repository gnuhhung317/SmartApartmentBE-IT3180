package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.annotations.QuickSearchDomain;
import com.hust.smart_apartment.dto.AbstractAuditingDto;
import com.hust.smart_apartment.entity.Resident;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@QuickSearchDomain(tableName = "apartments")
public class ApartmentResponse extends AbstractAuditingDto {
    private Long apartmentId;

    private String name;

    private String code;

    private Long area;

    private ResidentResponse owner;

    private List<Resident> residents;
}
