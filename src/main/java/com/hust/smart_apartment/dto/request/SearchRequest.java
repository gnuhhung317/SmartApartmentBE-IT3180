package com.hust.smart_apartment.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hust.smart_apartment.dto.model.Filter;
import com.hust.smart_apartment.dto.model.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class SearchRequest {
    @Schema(title = "page tìm kiếm: bắt đấu từ 1")
    private int page;
    @Schema(title = "từ khóa tìm kiếm")
    private String keyword; // q: quickSearch, f: filter, s: sort
    @Schema(title = "số lượng item của 1 page")
    private int pageSize = 10;
    private List<Order> sorts;
    private List<Filter> filters;
    private Map<String, Object> parametersForCustom;
    private Boolean isPermissionData = false;
    private Boolean isEmployee = false;

    @JsonIgnore
    public Integer getOffset() {
        return Math.max((page - 1) * pageSize, 0);
    }

    public int getPageSize() {
        if (this.pageSize < 0) return 500;
        return pageSize;
    }

    public List<Order> getSorts() {
        if (sorts == null) {
            sorts = new ArrayList<>();
        }
        return sorts;
    }

    public String getKeyword() {
        if (keyword == null) return null;
        return keyword.trim();
    }

    public List<Filter> getFilters() {
        if (filters == null) {
            filters = new ArrayList<>();
        }
        return filters;
    }

    public SearchRequest addFilter(Filter filter) {
        if (filters == null) {
            filters = new ArrayList<>();
        }
        filters.add(filter);
        return this;
    }
}