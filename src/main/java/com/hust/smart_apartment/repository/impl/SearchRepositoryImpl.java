package com.hust.smart_apartment.repository.impl;


import com.hust.smart_apartment.annotations.QuickSearchDomain;
import com.hust.smart_apartment.constants.AppConstants;
import com.hust.smart_apartment.constants.Operator;
import com.hust.smart_apartment.dto.model.QuickSearchInput;
import com.hust.smart_apartment.dto.model.QuickSearchParamsDTO;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.repository.SearchRepository;
import com.hust.smart_apartment.utils.DbMapper;
import com.hust.smart_apartment.utils.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Repository
@SuppressWarnings("java:S1192")
public class SearchRepositoryImpl<T> implements SearchRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private final DbMapper dbMapper;

    @Autowired
    public SearchRepositoryImpl(DbMapper dbMapper) {
        this.dbMapper = dbMapper;
    }

    @Override
    public Page<T> search(SearchRequest request, Class<T> clazz) {
        QuickSearchParamsDTO quickSearchParamsDTO = convertToQuickSearchParamsDTO(request, clazz);
        return quickSearch(quickSearchParamsDTO);
    }

    public Page<T> quickSearch(QuickSearchParamsDTO quickSearchParamsDTO) {
        StringBuilder sb = new StringBuilder("WITH ALL_DATA AS (SELECT COUNT(*) OVER() AS TOTAL, ")
                .append(String.join(AppConstants.CommonSymbol.COMMA, quickSearchParamsDTO.getColumns()))
                .append(" FROM ")
                .append(quickSearchParamsDTO.getTableName())
                .append(" WHERE 1=1 ");
        if (!StringUtil.isNullOrBlank(quickSearchParamsDTO.getWhereClause())) {
            sb.append(" AND ").append(quickSearchParamsDTO.getWhereClause());
        }
        if (!quickSearchParamsDTO.getConditionClauses().isEmpty()) {
            sb.append(" AND ")
                    .append(String.join(" AND ", quickSearchParamsDTO.getConditionClauses()))
                    .append(" ");
        }
        if (!quickSearchParamsDTO.getConditionClauseQuickSearch().isEmpty() && !StringUtil.isNullOrBlank(quickSearchParamsDTO.getTerm())) {
            sb.append(" AND ( ")
                    .append(String.join(" OR ", quickSearchParamsDTO.getConditionClauseQuickSearch()))
                    .append(" ) ");
        }

        if (!quickSearchParamsDTO.getSortClauses().isEmpty()) {
            sb.append(" ORDER BY ")
                    .append(String.join(AppConstants.CommonSymbol.COMMA, quickSearchParamsDTO.getSortClauses()));
        }
        sb.append(" ) SELECT * FROM ALL_DATA LIMIT :limit OFFSET :offset ");
        Query query = this.entityManager.createNativeQuery(sb.toString(), Tuple.class);
        if (!quickSearchParamsDTO.getConditionClauses().isEmpty()) {
            quickSearchParamsDTO.getParameters().forEach((key, value) -> {
                if (Objects.nonNull(value)) {
                    query.setParameter(key, value);
                }
            });
        }
        if (!quickSearchParamsDTO.getConditionClauseQuickSearch().isEmpty() && !StringUtil.isNullOrBlank(quickSearchParamsDTO.getTerm())) {
            query.setParameter(AppConstants.QueryParams.TERM_PARAM, quickSearchParamsDTO.getTerm());
        }
        if (Objects.nonNull(quickSearchParamsDTO.getParametersForCustom()) && !quickSearchParamsDTO.getParametersForCustom().isEmpty()) {
            quickSearchParamsDTO.getParametersForCustom().forEach((key, value) -> {
                if (Objects.nonNull(value)) {
                    query.setParameter(key, value);
                }
            });
        }

        query.setParameter(AppConstants.QueryParams.LIMIT_PARAM, quickSearchParamsDTO.getPageable().getPageSize());
        query.setParameter(AppConstants.QueryParams.OFFSET_PARAM, quickSearchParamsDTO.getPageable().getOffset());
        List<Tuple> results = query.getResultList();
        List<T> convertedResults = this.dbMapper.castSqlResult(results, quickSearchParamsDTO.getClazz()).stream().map(x -> (T) x).toList();
        long total = results.isEmpty() ? 0 : this.dbMapper.getLongSafe(results.getFirst(), "TOTAL");
        return new PageImpl<>(convertedResults, quickSearchParamsDTO.getPageable(), total);
    }

    private QuickSearchParamsDTO convertToQuickSearchParamsDTO(SearchRequest request, Class<?> clazz) {
        Map<String, DbMapper.DbFieldHolder> outputMap = new HashMap<>();
        this.dbMapper.getMethodMap(clazz, outputMap);
        QuickSearchDomain quickSearchDomain = clazz.getAnnotation(QuickSearchDomain.class);
        String tableName = quickSearchDomain.tableName().isEmpty() ? clazz.getSimpleName() : quickSearchDomain.tableName();
        List<String> orderClauses = request.getSorts().stream()
                .map(e -> StringUtil.camelToSnake(e.getProperty()) + AppConstants.CommonSymbol.SPACE + e.getDirection())
                .toList();
        List<String> conditionClauses = getConditionClauses(request);
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        Map<String, Object> parameters = new HashMap<>();
        request.getFilters().forEach(x -> {
            String name = x.getName();
            if (x.getOperation().equalsIgnoreCase(Operator.LESS_THAN_OR_EQUAL.getOperator()) ||
                    x.getOperation().equalsIgnoreCase(Operator.LESS_THAN.getOperator())) {
                name = name + "To";
            } else if (x.getOperation().equalsIgnoreCase(Operator.GREATER_THAN_OR_EQUAL.getOperator()) ||
                    x.getOperation().equalsIgnoreCase(Operator.GREATER_THAN.getOperator())) {
                name = name + "From";
            }
            if (!StringUtil.isNullOrBlank(x.getType()) && x.getType().equalsIgnoreCase("date")) {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse((String) x.getValue()).withZoneSameInstant(ZoneId.of("Asia/Ho_Chi_Minh"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppConstants.DateTimeFmt.YYYY_MM_DD_HH_MM_SS_ISO_T);
                parameters.put(name, LocalDateTime.parse(formatter.format(zonedDateTime)));
            } else
                parameters.put(name, x.getValue());
        });
        List<String> conditionListQuickSearch = new ArrayList<>();
        this.getConditionList(clazz, conditionListQuickSearch);
        return QuickSearchParamsDTO.builder()
                .term(request.getKeyword())
                .tableName(tableName)
                .columns(outputMap.keySet())
                .conditionClauses(conditionClauses)
                .parameters(parameters)
                .conditionClauseQuickSearch(conditionListQuickSearch)
                .sortClauses(orderClauses)
                .clazz(clazz)
                .pageable(pageable)
                .parametersForCustom(request.getParametersForCustom())
                .build();
    }

    private List<String> getConditionClauses(SearchRequest request) {
        List<String> conditions = new ArrayList<>();
        request.getFilters().forEach(filter -> {
            StringBuilder condition = new StringBuilder(StringUtil.camelToSnake(filter.getName()));
            switch (Operator.from(filter.getOperation())) {
                case EQUAL:
                    condition.append(" = :").append(filter.getName());
                    break;
                case NOT_EQUAL:
                    condition.append(" != :").append(filter.getName());
                    break;
                case GREATER_THAN:
                    condition.append(" > :").append(filter.getName()).append("From");
                    break;
                case LESS_THAN:
                    condition.append(" < :").append(filter.getName()).append("To");
                    break;
                case GREATER_THAN_OR_EQUAL:
                    condition.append(" >= :").append(filter.getName()).append("From");
                    break;
                case LESS_THAN_OR_EQUAL:
                    condition.append(" <= :").append(filter.getName()).append("To");
                    break;
                case LIKE:
                    condition.append(" LIKE CONCAT('%',:").append(filter.getName()).append(",'%')").append(" COLLATE utf8mb4_general_ci ");
                    break;
                case IN:
                    condition.append(" IN :").append(filter.getName());
                    break;
                case NIN:
                    condition.append(" NOT IN :").append(filter.getName());
                    break;
                case IS_NULL:
                    condition.append(" IS NULL");
                    break;
                case IS_NOT_NULL:
                    condition.append(" IS NOT NULL");
                    break;
                default:
                    // Handle other cases or ignore
                    break;
            }
            conditions.add(condition.toString());
        });
        return conditions;
    }

    protected <R> void getConditionList(Class<R> clazz, List<String> conditionList) {
        if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
            this.getConditionList(clazz.getSuperclass(), conditionList);
        }

        for (Field field : clazz.getDeclaredFields()) {
            QuickSearchInput quickSearchInput = field.getAnnotation(QuickSearchInput.class);
            if (quickSearchInput == null) {
                continue;
            }
            String cond = quickSearchInput.keyOption().getValue()
                    .replace("%COLUMN_NAME%", quickSearchInput.columnName())+" COLLATE utf8mb4_general_ci ";
            conditionList.add(cond);
        }
    }
}
