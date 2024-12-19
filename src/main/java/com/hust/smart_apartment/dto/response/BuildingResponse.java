package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.annotations.DbColumnMapper;
import com.hust.smart_apartment.annotations.QuickSearchDomain;
import com.hust.smart_apartment.constants.QuickSearchKeyOption;
import com.hust.smart_apartment.dto.model.QuickSearchInput;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@QuickSearchDomain(tableName = "buildings")
public class BuildingResponse {

    @DbColumnMapper("id")
    private Long id;

    @QuickSearchInput(columnName = "building_code",keyOption = QuickSearchKeyOption.ILIKE)
    @DbColumnMapper("building_code")
    private String buildingCode;

    @QuickSearchInput(columnName = "building_name",keyOption = QuickSearchKeyOption.ILIKE)
    @DbColumnMapper("building_name")
    private String buildingName;
}
