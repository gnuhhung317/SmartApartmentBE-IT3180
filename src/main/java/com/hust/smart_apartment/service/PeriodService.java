package com.hust.smart_apartment.service;

import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.PeriodRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.PeriodResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface PeriodService {
    PeriodResponse getById(Long id);
    PeriodResponse create(PeriodRequest request);
    PeriodResponse update(Long id,PeriodRequest request);
    ModifyDto delete(Long id);
    Page<PeriodResponse> search(SearchRequest request);
}
