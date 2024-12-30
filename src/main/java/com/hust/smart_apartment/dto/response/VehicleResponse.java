package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.annotations.DbColumnMapper;
import com.hust.smart_apartment.annotations.QuickSearchDomain;
import com.hust.smart_apartment.constants.FeeCategory;
import com.hust.smart_apartment.constants.QuickSearchKeyOption;
import com.hust.smart_apartment.dto.AbstractAuditingDto;
import com.hust.smart_apartment.dto.model.QuickSearchInput;
import com.hust.smart_apartment.entity.Apartment;
import com.hust.smart_apartment.entity.VehicleType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@QuickSearchDomain(tableName = "vehicles_search_view")
public class VehicleResponse extends AbstractAuditingDto {

    @DbColumnMapper("vehicle_id")
    private Long vehicleId;

    @DbColumnMapper("license_plate")
    @QuickSearchInput(columnName = "license_plate",keyOption = QuickSearchKeyOption.ILIKE)
    private String licensePlate;

    @DbColumnMapper("name")
    @QuickSearchInput(columnName = "name",keyOption = QuickSearchKeyOption.ILIKE)
    private String name;

    @DbColumnMapper("apartment_id")
    private Long apartmentId;

    @DbColumnMapper("vehicle_type_id")
    private Long vehicleTypeId;

    @DbColumnMapper("vehicle_type_name")
    private String vehicleTypeName;

    @DbColumnMapper("apartment_name")
    private String apartmentName;

    @DbColumnMapper("apartment_code")
    private String apartmentCode;

    private VehicleTypeResponse vehicleType;

    private ApartmentResponse apartment;

}
