package com.hust.smart_apartment.dto.response;

import com.hust.smart_apartment.annotations.DbColumnMapper;
import com.hust.smart_apartment.annotations.QuickSearchDomain;
import com.hust.smart_apartment.constants.QuickSearchKeyOption;
import com.hust.smart_apartment.dto.model.QuickSearchInput;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@QuickSearchDomain(tableName = "floors")
public class FloorResponse {

    @DbColumnMapper("id")
    private Long id;

    @QuickSearchInput(columnName = "name",keyOption = QuickSearchKeyOption.ILIKE)
    @DbColumnMapper("name")
    private String name;

    @DbColumnMapper("floor_number")
    private Integer floorNumber;
    private BuildingResponse building;
    private List<ApartmentResponse> apartments;
}
