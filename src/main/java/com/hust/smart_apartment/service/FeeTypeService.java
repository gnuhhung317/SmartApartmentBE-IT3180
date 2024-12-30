package com.hust.smart_apartment.service;

import com.hust.smart_apartment.constants.FeeCategory;
import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.FeeTypeRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.FeeTypeResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface FeeTypeService {
    FeeTypeResponse getById(Long id);
    FeeTypeResponse create(FeeTypeRequest request);
    FeeTypeResponse update(Long id,FeeTypeRequest request);
    ModifyDto update(FeeCategory category, FeeTypeRequest request);
    ModifyDto delete(Long id);
    Page<FeeTypeResponse> search(SearchRequest request);
}
