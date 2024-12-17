package com.hust.smart_apartment.dto.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuickSearchParamsDTO {

    private String tableName; // Name of table

    private Set<String> columns; // Columns

    private String whereClause; // Conditions of where

    private List<String> conditionClauses; // Conditions of clauses

    Map<String, Object> parameters; // parameters to conditionClauses

    private List<String> sortClauses; // sort clauses

    List<String> conditionClauseQuickSearch; // conditions for quick search

    private String term; // parameters for quickSearch

    private Class<?> clazz; // clazz response

    private Pageable pageable; // pageable

    private Map<String, Object> parametersForCustom;

    private Boolean isPermissionData;

    private Boolean isEmployee;
}
