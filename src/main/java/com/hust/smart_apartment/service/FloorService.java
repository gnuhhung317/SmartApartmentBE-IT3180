package com.hust.smart_apartment.service;

import com.hust.smart_apartment.dto.request.FloorRequest;
import com.hust.smart_apartment.dto.request.SearchRequest;
import com.hust.smart_apartment.dto.response.FloorResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface FloorService {

    FloorResponse create(FloorRequest request);
    Page<FloorResponse> search(SearchRequest request);

}
