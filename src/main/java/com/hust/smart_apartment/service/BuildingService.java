package com.hust.smart_apartment.service;

import com.hust.smart_apartment.dto.ModifyDto;
import com.hust.smart_apartment.dto.request.BuildingRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.BuildingResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface BuildingService {
    BuildingResponse getById(Long id);
    BuildingResponse create(BuildingRequest request);
    BuildingResponse update(Long id,BuildingRequest request);
    ModifyDto delete(Long id);
    Page<BuildingResponse> search(SearchRequest request);
}
